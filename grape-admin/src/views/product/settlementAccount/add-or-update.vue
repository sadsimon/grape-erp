<template>
	<GrDialog v-model="visible" append-to-body :title="!update ? '新增' : '修改'" :close-on-click-modal="false" draggable>
		<el-form ref="dataFormRef" :model="dataForm" :rules="dataRules" label-width="120px" @keyup.enter="submitHandle()">
			<el-form-item prop="accountCode" label="账户编号">
				<el-input v-model="dataForm.accountCode"></el-input>
			</el-form-item>
			<el-form-item prop="accountName" label="账户名称">
				<el-input v-model="dataForm.accountName"></el-input>
			</el-form-item>

			<el-form-item prop="openingBalance" label="期初余额">
				<el-input v-model="dataForm.openingBalance"></el-input>
			</el-form-item>
			<el-form-item prop="accountType" label="账户类型">
				<ma-dict-select v-model="dataForm.accountType" dict-type="account_type" clearable></ma-dict-select>
			</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="submitHandle()">确定</el-button>
		</template>
	</GrDialog>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus/es'
import { useSettlementAccountSubmitApi } from '@/api/product/product'

const emit = defineEmits(['refreshDataList'])

const visible = ref(false)
const dataFormRef = ref()

const dataForm = reactive({
	id: '',
	accountCode: '',
	accountName: '',
	openingBalance: '',
	accountType: ''
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
		getBrand(isUpdate, row)
	} else {
		dataForm.id = ''
		dataForm.accountCode = ''
		dataForm.accountName = ''
		dataForm.openingBalance = ''
		dataForm.accountType = ''
	}
}

// 获取信息
const getBrand = (isUpdate: boolean, row: any) => {
	Object.assign(dataForm, row)
	if (!isUpdate) {
		// 是新增，重置表单数据
		dataForm.id = ''
		dataForm.accountCode = ''
		dataForm.accountName = ''
		dataForm.openingBalance = ''
		dataForm.accountType = ''
	}
}

const dataRules = ref({
	accountCode: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
	accountName: [{ required: true, message: '必填项不能为空', trigger: 'blur' }]
})

// 表单提交
const submitHandle = () => {
	dataFormRef.value.validate((valid: boolean) => {
		if (!valid) {
			return false
		}

		useSettlementAccountSubmitApi(dataForm).then(() => {
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
