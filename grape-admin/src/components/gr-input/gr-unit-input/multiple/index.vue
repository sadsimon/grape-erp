<template>
	<ma-data-select
		ref="maDataSelectRef"
		v-model="model"
		:url="'/product/grUnit/list/2'"
		:props="{ label: 'manyName', value: 'id' }"
		:placeholder
		filterable
		:style="{ width: width }"
	>
		<template #footer>
			<el-button @click="addOrUpdateHandle(false)">新增</el-button>
			<el-button @click="refreshDataList()">刷新</el-button>
		</template>
	</ma-data-select>

	<GrMultipleUnitAddOrUpdate ref="addOrUpdateRef" @refresh-data-list="refreshDataList"></GrMultipleUnitAddOrUpdate>
</template>

<script setup lang="ts" name="GrMultipleUnitInput">
import { reactive, ref } from 'vue'

defineProps({
	placeholder: {
		type: String,
		required: false,
		default: ''
	},
	width: {
		type: String,
		required: false,
		default: '100%'
	}
})
const model = defineModel<number | string>()

const addOrUpdateRef = ref()
const addOrUpdateHandle = (isUpdate: Boolean, row?: any) => {
	addOrUpdateRef.value.init(isUpdate, row)
}

interface MaDataSelect {
	refresh: () => void
}
const maDataSelectRef = ref<MaDataSelect | null>(null) // MaDataSelect 的 ref
const refreshDataList = () => {
	if (maDataSelectRef.value) {
		maDataSelectRef.value.refresh()
	} else {
		console.error('maDataSelectRef.value is null')
	}
}
</script>

<style></style>
