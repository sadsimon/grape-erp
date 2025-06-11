import service from '@/utils/request'

export const useFieldSubmitApi = (dataForm: any) => {
	return service.post('/system/grField', dataForm)
}

export const useFieldRestoreDefaultApi = (dataForm: any) => {
	return service.post('/system/grField/restoreDefault?code=' + dataForm)
}