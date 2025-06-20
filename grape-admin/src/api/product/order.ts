import service from '@/utils/request'

export const useGetDocumentCodeApi = (type: string) => {
	return service.post('/order/grDocument/getDocumentCode?type=' + type)
}

//入库明细
export const useGetDocumentDetailApi = (documentId: number) => {
	return service.get('/order/grDocumentDetail/list?documentId=' + documentId)
}

//结算明细
export const useGetDocumentAccountDetailApi = (documentId: number) => {
	return service.get('/order/grDocumentAccountDetail/list?documentId=' + documentId)
}

//单据
export const useDocumentSubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/order/grDocument', dataForm)
	} else {
		return service.post('/order/grDocument', dataForm)
	}
}

export const useDocumentApi = (id: Number) => {
	return service.get('/order/grDocument/' + id)
}

/**
 * 财务
 */
//获取此前应付
export const useGetHistoryPayAmountApi = (contactunitsId: number | null) => {
	return service.get(`/product/grContactunits/getHistoryPayAmount?contactunitsId=${contactunitsId}`)
}

//获取此前应收
export const useGetgetHistoryReceivePaymentApi = (contactunitsId: number | null) => {
	return service.get(`/product/grContactunits/getHistoryReceivePayment?contactunitsId=${contactunitsId}`)
}
