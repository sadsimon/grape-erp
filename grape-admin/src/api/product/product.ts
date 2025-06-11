import service from '@/utils/request'

//商品
export const useProductSubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/product/grProduct', dataForm)
	} else {
		return service.post('/product/grProduct', dataForm)
	}
}

export const useProductApi = (id: Number) => {
	return service.get('/product/grProduct/' + id)
}

//单位
export const useUnitSubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/product/grUnit', dataForm)
	} else {
		return service.post('/product/grUnit', dataForm)
	}
}

export const useUnitApi = (id: Number) => {
	return service.get('/product/grUnit/' + id)
}

export const useUnitListApi = (unitId: Number) => {
	return service.get('/product/grProductBarcode/list/' + unitId)
}

//品牌
export const useBrandSubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/product/grBrand', dataForm)
	} else {
		return service.post('/product/grBrand', dataForm)
	}
}

//收支项目
export const useIncomeExpendSubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/product/grIncomeExpend', dataForm)
	} else {
		return service.post('/product/grIncomeExpend', dataForm)
	}
}

//业务员
export const useSettlementAccountSubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/product/grSettlementAccount', dataForm)
	} else {
		return service.post('/product/grSettlementAccount', dataForm)
	}
}

//条码精准查询
export const useBarcodeApi = (barcode: string) => {
	return service.get('/product/grProductBarcode/getByBarcode?barcode=' + barcode)
}

//条码模糊查询
export const useListByBarcodeApi = (barcode: string) => {
	return service.get('/product/grProductBarcode/getListByBarcode?barcode=' + barcode)
}
