import service from '@/utils/request'
import { ElMessage, ElMessageBox } from 'element-plus'

export const useDataApi = async (url: string) => {
	const { data } = await service.get(url)

	return data
}

export const deleteHandle = (key: number | string, url: string, onSuccess?: () => void) => {
	let data: any[] = []
	if (key) {
		data = [key]
	} /* else {
    data = state.dataListSelectionKeys ? state.dataListSelectionKeys : [];

    if (data.length === 0) {
      ElMessage.warning('请选择删除记录');
      return;
    }
  } */

	ElMessageBox.confirm('确定进行删除操作?', '提示', {
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		type: 'warning'
	})
		.then(() => {
			if (url) {
				service
					.delete(url, { data })
					.then(() => {
						ElMessage.success('删除成功')
						if (onSuccess) {
							onSuccess() // 调用回调函数
						}
					})
					.catch(error => {
						ElMessage.error('删除失败，请稍后再试')
						console.error('删除失败:', error)
					})
			}
		})
		.catch(() => {
			// 用户取消操作
		})
}

export const stateHandle = (key: number, state: number, url: string) => {
	let data: { id: number; state: number }
	data = { id: key, state: state }
	if (url) {
		service
			.put(url, data)
			.then(() => {
				ElMessage.success('操作成功')
			})
			.catch(error => {
				ElMessage.error('操作失败，请稍后再试')
				console.error('操作失败:', error)
			})
	}
}
