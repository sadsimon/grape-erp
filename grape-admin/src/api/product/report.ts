import service from '@/utils/request'

//首页报表
export const useHomeDataApi = (type: string) => {
	return service.get('/report/getHomeData?type='+ type)
}