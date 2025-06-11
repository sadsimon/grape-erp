<template>
	<GrDialog v-model="visible" append-to-body width="500px" :title="!update ? '新增' : '修改'" :close-on-click-modal="false" draggable>
		<el-form ref="dataFormRef" :model="dataForm" label-width="120px" @keyup.enter="submitHandle()">
			<el-form-item prop="manyName" label="多单位名称">
				<el-input v-model="dataForm.manyName" style="width: 150px"></el-input>
			</el-form-item>
			<el-form-item
				prop="name"
				label="基本单位"
				:rules="{
					required: true,
					message: '基本单位不能为空',
					trigger: 'blur'
				}"
			>
				<el-space :size="5"><el-input v-model="dataForm.name" style="width: 150px"></el-input> <el-text class="mx-1">基本单位应为最小度量单位</el-text></el-space>
			</el-form-item>
			<el-form-item
				v-for="(viceUnit, index) in dataForm.viceUnitList"
				:key="viceUnit.key"
				:label="'副单位' + (index + 1)"
				:prop="'viceUnitList.' + index + '.name'"
				:rules="{
					required: true,
					message: '副单位不能为空',
					trigger: 'blur'
				}"
			>
				<el-input v-model="viceUnit.name" style="margin-right: 10px; width: 150px" /> =
				<el-input v-model="viceUnit.capacity" style="margin-left: 10px; width: 100px"
					><template #append>{{ dataForm.name }}</template></el-input
				>
				<el-button :icon="Delete" style="margin-left: 10px" @click.prevent="removeDomain(viceUnit)" />
			</el-form-item>

			<el-button icon="Plus" type="primary" @click="addDomain">增加单位</el-button>
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="submitHandle()">确定</el-button>
		</template>
	</GrDialog>
</template>

<script setup lang="ts" name="GrMultipleUnitAddOrUpdate">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus/es'
import { Delete } from '@element-plus/icons-vue'
import { useUnitSubmitApi, useUnitApi } from '@/api/product/product'

const dataForm = reactive<{
	viceUnitList: ViceUnit[]
	viceUnitListDelete: number[]
	manyName: string
	name: string
	id: number | null
	model: string
}>({
	viceUnitList: [
		{
			key: 1,
			id: null,
			name: '',
			capacity: 0,
			deleted: 0
		}
	],
	viceUnitListDelete: [],
	manyName: '',
	name: '',
	id: null,
	model: '2'
})

interface ViceUnit {
	key: number
	id: number | null
	name: string
	capacity: number | null
	deleted: number
}

const removeDomain = (item: ViceUnit) => {
	const index = dataForm.viceUnitList.indexOf(item)
	if (index !== -1) {
		dataForm.viceUnitList.splice(index, 1)
		if (item.id) {
			dataForm.viceUnitListDelete.push(item.id)
		}
	}
}

const addDomain = () => {
	dataForm.viceUnitList.push({
		key: Date.now(),
		id: null,
		name: '',
		capacity: null,
		deleted: 0
	})
}

const emit = defineEmits(['refreshDataList'])

const visible = ref(false)
const dataFormRef = ref()
const update = ref(false)

const init = (isUpdate: boolean, id: any) => {
	visible.value = true
	update.value = isUpdate

	// 重置表单数据
	if (dataFormRef.value) {
		dataFormRef.value.resetFields()
	}

	// 存在则为修改
	if (id) {
		getUnit(isUpdate, id)
	} else {
		dataForm.manyName = ''
		dataForm.id = null
		dataForm.model = '2'
		dataForm.viceUnitList = []
		dataForm.name = ''
		dataForm.viceUnitListDelete = []
	}
}

// 获取信息
const getUnit = (isUpdate: boolean, id: any) => {
	useUnitApi(id).then(res => {
		Object.assign(dataForm, res.data)
		//初始化viceUnitListDelete
		dataForm.viceUnitListDelete = []
	})
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
