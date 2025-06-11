import service from '@/utils/request'

export const useCustomerSubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/product/grCustomer', dataForm)
	} else {
		return service.post('/product/grCustomer', dataForm)
	}
}

export const useCustomerApi = (id: Number) => {
	return service.get('/product/grCustomer/' + id)
}

export const useCustomerNameListApi = (idList: number[]) => {
	return service.post('/product/grCustomer/customerNameList', idList)
}