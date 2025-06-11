<template>
	<GrDialog v-model="visible" append-to-body width="500px" :title="!update ? '新增' : '修改'" :close-on-click-modal="false" draggable>
		<el-form ref="dataFormRef" :model="dataForm" label-width="120px" @keyup.enter="submitHandle()">
			<el-form-item prop="storeNumber" label="仓库编号">
				<el-input v-model="dataForm.storeNumber"></el-input>
			</el-form-item>
			<el-form-item prop="storeName" label="仓库名称">
				<el-input v-model="dataForm.storeName"></el-input>
			</el-form-item>
			<el-form-item prop="userId" label="仓管">
				<ma-user-input v-model="dataForm.userId"></ma-user-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="submitHandle()">确定</el-button>
		</template>
	</GrDialog>
</template>

<script setup lang="ts" name="StoreAddOrUpdate">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus/es'
import { useStoreSubmitApi } from '@/api/product/store'

const emit = defineEmits(['refreshDataList'])

const visible = ref(false)
const dataFormRef = ref()

const dataForm = reactive({
	id: '',
	storeNumber: '',
	storeName: '',
	userId: ''
})

const update = ref(false)

const init = (isUpdate: boolean, row: any) => {
	visible.value = true
	update.value = isUpdate

	// 重置表单数据
	if (dataFormRef.value) {
		dataFormRef.value.resetFields()
	}

	// 存在则为修改
	if (row) {
		getStore(isUpdate, row)
	} else {
		dataForm.storeNumber = ''
		dataForm.storeName = ''
		dataForm.storeName = ''
		dataForm.userId = ''
	}
}

// 获取信息
const getStore = (isUpdate: boolean, row: any) => {
	Object.assign(dataForm, row)
	if (!isUpdate) {
		// 是新增，重置表单数据
		dataForm.id = ''
		dataForm.storeNumber = ''
		dataForm.storeName = ''
		dataForm.userId = ''
	}
}

// 表单提交
const submitHandle = () => {
	dataFormRef.value.validate((valid: boolean) => {
		if (!valid) {
			return false
		}

		useStoreSubmitApi(dataForm).then(() => {
			ElMessage.success({
				message: '操作成功',
				duration: 500,
				onClose: () => {
					visible.value = false
					emit('refreshDataList')
				}
			})
		})
	})
}

defineExpose({
	init
})
</script>

<style lang="scss" scoped></style>
