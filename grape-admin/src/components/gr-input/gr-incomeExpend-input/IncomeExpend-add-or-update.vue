<template>
	<GrDialog width="600px" v-model="visible" :title="!update ? '新增' : '修改'" :close-on-click-modal="false" draggable>
		<el-form ref="dataFormRef" :model="dataForm" :rules="dataRules" label-width="120px" @keyup.enter="submitHandle()">
			<el-form-item prop="name" label="名称">
				<el-input v-model="dataForm.name"></el-input>
			</el-form-item>
			<el-form-item prop="name" label="类型">
				<el-select
					  v-model="dataForm.amountType"
					>
					  <el-option label= "收入" value="1" />
					  <el-option label="支出" value="2" />
				</el-select>
			</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="submitHandle()">确定</el-button>
		</template>
	</GrDialog>
</template>

<script setup lang="ts" name="IncomeExpendAddOrUpdate">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus/es'
import { useIncomeExpendSubmitApi } from '@/api/product/product'

const emit = defineEmits(['refreshDataList'])

const visible = ref(false)
const dataFormRef = ref()

const dataForm = reactive({
	id: '',
	name: '',
	amountType: ''
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
		getIncomeExpenditure(isUpdate, row)
	} else {
		dataForm.id = ''
		dataForm.name = ''
		dataForm.amountType = ''
	}
}

// 获取信息
const getIncomeExpenditure = (isUpdate: boolean, row: any) => {
	Object.assign(dataForm, row)
	if (!isUpdate) {
		// 是新增，重置表单数据
		dataForm.id = ''
		dataForm.name = ''
		dataForm.amountType = ''
	}
}

const dataRules = ref({
	name: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
	amountType: [{ required: true, message: '必填项不能为空', trigger: 'blur' }]
})

// 表单提交
const submitHandle = () => {
	dataFormRef.value.validate((valid: boolean) => {
		if (!valid) {
			return false
		}

		useIncomeExpendSubmitApi(dataForm).then(() => {
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
