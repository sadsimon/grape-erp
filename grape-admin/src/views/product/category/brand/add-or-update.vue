<template>
	<GrDialog v-model="visible" :title="!update ? '新增' : '修改'" draggable>
		<el-form ref="dataFormRef" :model="dataForm" :rules="dataRules" label-width="120px" @keyup.enter="submitHandle()">
			<el-form-item prop="pid" label="上级类别">
				<el-tree-select
					v-model="dataForm.pid"
					:data="brandCategoryList"
					value-key="id"
					check-strictly
					:render-after-expand="false"
					:props="{ label: 'categoryName', children: 'children' }"
					style="width: 100%"
					clearable
			/></el-form-item>
			<el-form-item prop="categoryName" label="名称">
				<el-input v-model="dataForm.categoryName" placeholder="名称"></el-input>
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
import { useBrandCategoryListApi, useBrandCategorySubmitApi } from '@/api/product/category'

const emit = defineEmits(['refreshDataList'])

const visible = ref(false)
const brandCategoryList = ref([])
const dataFormRef = ref()

const dataForm = reactive({
	id: '',
	pid: '',
	categoryName: '',
	parentName: ''
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
		getBrandCategory(isUpdate, row)
	} else {
		dataForm.pid = ''
		dataForm.categoryName = ''
	}

	// 品牌分类列表
	getBrandCategoryList()
}

// 获取品牌分类列表
const getBrandCategoryList = async () => {
	const res = await useBrandCategoryListApi()
	brandCategoryList.value = res.data
}

// 获取信息
const getBrandCategory = (isUpdate: boolean, row: any) => {
	Object.assign(dataForm, row)
	if (!isUpdate) {
		// 是新增，重置表单数据
		dataForm.pid = row.id
		dataForm.id = ''
		dataForm.categoryName = ''
		dataForm.parentName = ''
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

		useBrandCategorySubmitApi(dataForm).then(() => {
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

<style lang="scss" scoped>
.mod__brandCategory {
	::v-deep(.el-popover.el-popper) {
		overflow-x: hidden;
	}

	.popover-list {
		::v-deep(.el-input__inner) {
			cursor: pointer;
		}
		::v-deep(.el-input__suffix) {
			cursor: pointer;
		}
	}
}
</style>
