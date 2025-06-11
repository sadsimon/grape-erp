<template>
	<GrDialog v-model="visible" width="600px" :title="!update ? '新增' : '修改'" :close-on-click-modal="false" draggable>
		<el-form ref="dataFormRef" :model="dataForm" :rules="dataRules" label-width="120px" @keyup.enter="submitHandle()">
			<el-form-item prop="brandCategoryId" label="品牌类别">
				<ma-data-tree-select
					v-model="dataForm.brandCategoryId"
					url="/product/grBrandCategory/list"
					:props="{ label: 'categoryName', children: 'children' }"
				></ma-data-tree-select>
			</el-form-item>
			<el-form-item prop="brandCode" label="品牌编码">
				<el-input v-model="dataForm.brandCode"></el-input>
			</el-form-item>

			<el-form-item prop="brandName" label="品牌名称">
				<el-input v-model="dataForm.brandName"></el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="submitHandle()">确定</el-button>
		</template>
	</GrDialog>
</template>

<script setup lang="ts" name="BrandAddOrUpdate">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus/es'
import { useBrandSubmitApi } from '@/api/product/product'

const emit = defineEmits(['refreshDataList'])

const visible = ref(false)
const dataFormRef = ref()

const dataForm = reactive({
	id: '',
	brandCategoryId: '',
	brandCode: '',
	brandName: ''
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
		dataForm.brandCategoryId = ''
		dataForm.brandCode = ''
		dataForm.brandName = ''
	}
}

// 获取信息
const getBrand = (isUpdate: boolean, row: any) => {
	Object.assign(dataForm, row)
	if (!isUpdate) {
		// 是新增，重置表单数据
		dataForm.brandCategoryId = ''
		dataForm.id = ''
		dataForm.brandCode = ''
		dataForm.brandName = ''
	}
}

const dataRules = ref({
	categoryName: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
	parentName: [{ required: true, message: '必填项不能为空', trigger: 'blur' }]
})

// 表单提交
const submitHandle = () => {
	dataFormRef.value.validate((valid: boolean) => {
		if (!valid) {
			return false
		}

		useBrandSubmitApi(dataForm).then(() => {
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
