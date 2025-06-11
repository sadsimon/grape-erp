<template>
	<GrDialog v-model="visible" append-to-body width="500px" :title="!update ? '新增' : '修改'" :close-on-click-modal="false" draggable>
		<el-form ref="dataFormRef" :model="dataForm" label-width="120px" @keyup.enter="submitHandle()">
			<el-form-item prop="name" label="单位名称">
				<el-input v-model="dataForm.name"></el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="submitHandle()">确定</el-button>
		</template>
	</GrDialog>
</template>

<script setup lang="ts" name="UnitSingleAddOrUpdate">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus/es'
import { useUnitSubmitApi } from '@/api/product/product'

const emit = defineEmits(['refreshDataList'])

const visible = ref(false)
const productCategoryList = ref([])
const dataFormRef = ref()

const dataForm = reactive({
	name: '',
	id: '',
	model: ''
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
		getUnit(isUpdate, row)
	} else {
		dataForm.name = ''
		dataForm.id = ''
		dataForm.model = '1'
	}
}

// 获取信息
const getUnit = (isUpdate: boolean, row: any) => {
	Object.assign(dataForm, row)
	if (!isUpdate) {
		// 是新增，重置表单数据
		dataForm.name = ''
		dataForm.id = ''
		dataForm.model = '1'
	}
}

// 表单提交
const submitHandle = () => {
	dataFormRef.value.validate((valid: boolean) => {
		if (!valid) {
			return false
		}

		useUnitSubmitApi(dataForm).then(() => {
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
