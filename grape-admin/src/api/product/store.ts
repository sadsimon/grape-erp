import service from '@/utils/request'

export const useStoreSubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/product/grStore', dataForm)
	} else {
		return service.post('/product/grStore', dataForm)
	}
}

export const useStoreListApi = () => {
	return service.get('/product/grStore/list')
}

export const useProductStockApi = (productId: number) => {
	return service.get('/product/grProductStock/list?productId='+productId)
}

export const useCurrentStock = (productId: number | null,storeId: number | null) => {
	return service.get(`/stock/grCurrentStock/getCurrentStock?productId=${productId}&storeId=${storeId}`)
}