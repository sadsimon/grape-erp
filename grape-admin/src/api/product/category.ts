import service from '@/utils/request'

//商品分类
export const useProductCategoryListApi = () => {
	return service.get('/product/grProductCategory/list')
}

export const useProductCategoryApi = (id: Number) => {
	return service.get('/product/grProductCategory' + id)
}

export const useProductCategorySubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/product/grProductCategory', dataForm)
	} else {
		return service.post('/product/grProductCategory', dataForm)
	}
}

//供应商分类
export const useContactunitsCategoryListApi = () => {
	return service.get('/product/grContactunitsCategory/list')
}

export const useContactunitsCategoryApi = (id: Number) => {
	return service.get('/product/grContactunitsCategory' + id)
}

export const useContactunitsCategorySubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/product/grContactunitsCategory', dataForm)
	} else {
		return service.post('/product/grContactunitsCategory', dataForm)
	}
}

//品牌分类
export const useBrandCategoryListApi = () => {
	return service.get('/product/grBrandCategory/list')
}

export const useBrandCategoryApi = (id: Number) => {
	return service.get('/product/grBrandCategory' + id)
}

export const useBrandCategorySubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/product/grBrandCategory', dataForm)
	} else {
		return service.post('/product/grBrandCategory', dataForm)
	}
}
