import service from '@/utils/request'

export const useContactunitsSubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/product/grContactunits', dataForm)
	} else {
		return service.post('/product/grContactunits', dataForm)
	}
}

export const useContactunitsApi = (id: Number) => {
	return service.get('/product/grContactunits/' + id)
}

export const useContactunitsNameListApi = (idList: number[]) => {
	return service.post('/product/grContactunits/contactunitsNameList', idList)
}
