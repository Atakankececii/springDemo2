package com.etiya.northwind.business.concretes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.etiya.northwind.business.abstracts.CategoryService;
import com.etiya.northwind.business.requests.categories.CreateRequestCategory;
import com.etiya.northwind.business.requests.categories.DeleteRequestCategory;
import com.etiya.northwind.business.requests.categories.UpdateRequestCategory;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.business.responses.categories.GetCategoryResponse;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.core.utilities.results.SuccessDataResult;
import com.etiya.northwind.core.utilities.results.SuccessResult;
import com.etiya.northwind.dataAccess.abstracts.CategoryRepository;
import com.etiya.northwind.entities.concretes.Category;

@Service
public class CategoryManager implements CategoryService {

	private CategoryRepository categoryRepository;
	private ModelMapperService modelMapperService;
	
	
	
	@Autowired
	public CategoryManager(CategoryRepository categoryRepository, ModelMapperService modelMapperService) {
		
		this.categoryRepository = categoryRepository;
		this.modelMapperService = modelMapperService;
	}
	





	@Override
	public DataResult<List<CategoryListResponse>> getAll() {
		List<Category> result = this.categoryRepository.findAll();
		List<CategoryListResponse> response = result.stream().map(category->
		this.modelMapperService.forResponse().map(category, CategoryListResponse.class)).collect(
				Collectors.toList());		
		
		return new SuccessDataResult<List<CategoryListResponse>>(response);
	}



	@Override
	public Result add(CreateRequestCategory createRequestCategory) {
		
		checkCategoryNameExists(createRequestCategory.getCategoryName());
		Category category = this.modelMapperService.forRequest().
				map(createRequestCategory, Category.class);
		this.categoryRepository.save(category);
		
		return new SuccessResult("Category added");
		
	}



	@Override
	public DataResult<GetCategoryResponse> getById(int categoryId) {
		
		Category category = this.categoryRepository.getById(categoryId);
		GetCategoryResponse getCategoryResponse = this.modelMapperService.forResponse().map(category, GetCategoryResponse.class);	
		return new SuccessDataResult<GetCategoryResponse>(getCategoryResponse);
	}




	@Override
	public Result update(UpdateRequestCategory updateRequestCategory) {
		Category category = this.modelMapperService.forResponse().map(updateRequestCategory, Category.class);
		this.categoryRepository.save(category);
		return new SuccessResult("Category updated");
	}






	@Override
	public Result delete(DeleteRequestCategory deleteRequestCategory) {
		this.categoryRepository.deleteById(deleteRequestCategory.getCateogryId());
		return new SuccessResult("Product deleted");
	}
	
	
	private void checkCategoryNameExists(String categoryName) {
		
		for (Category category : this.categoryRepository.findAll()) {
			if (category.getCategoryName().equals(categoryName)) {
				throw new BusinessException("Category name exists");
			}
			
		}
		
	}






//	@Override
//	public List<Category> findCategoriesWithSorting(String field) {
//		 return  categoryRepository.findAll(Sort.by(Sort.Direction.ASC,field));
//	}
//
//
//
//
//
//
//	@Override
//	public Page<Category> findCategoriesWithPagination(int offset, int pageSize) {
//        Page<Category> categories = categoryRepository.findAll(PageRequest.of(offset, pageSize));
//        return  categories;
//	}
//
//
//
//
//
//
//	@Override
//	public Page<Category> findCategoriesWithPaginationAndSorting(int offset, int pageSize, String field) {
//		  Page<Category> categories = categoryRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
//	        return  categories;
//	}






	@Override
	public DataResult<List<CategoryListResponse>> getAllPages(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		List<Category> categories = this.categoryRepository.findAll(pageable).getContent();
		List<CategoryListResponse> response = categories.stream().map(category->
		this.modelMapperService.forResponse().map(category, CategoryListResponse.class)).collect(
				Collectors.toList());
		
		int currentPage = pageNo;
		int totalDatas = categories.size();
		int totalPages = (int) Math.ceil(totalDatas/pageSize);
		
		return new SuccessDataResult<List<CategoryListResponse>>(response);
	}



	  @Override
	    public Map<String, Object> getAllPagesSort(int pageNumber, int pageSize,String entity,String type) {
	        Pageable pageable=PageRequest.of(pageNumber-1,pageSize,sortType(entity,type));


	        Map<String,Object> response=new HashMap<>();
	        Page<Category>categories =categoryRepository.findAll(pageable);
	        response.put("totalElements",categories.getTotalElements()) ;
	        response.put("totalPages",categories.getTotalPages());
	        response.put("currentPage",categories.getNumber()+1);
	        response.put("categories",categories.getContent().stream().map(category ->
	                this.modelMapperService.forResponse().map(category,CategoryListResponse.class)).collect(Collectors.toList()));

	        return response ;
	    }


	 public Sort sortType(String property,String type){
	        if(type.equals("desc"))
	            return Sort.by(property).descending();
	        else return Sort.by(property).ascending() ;

	    }





}
