<template>
	<ma-data-select
		ref="maDataSelectRef"
		v-model="model"
		:url="'/product/grUnit/list/1'"
		:props="{ label: 'name', value: 'id' }"
		:placeholder
		filterable
		:style="{ width: width }"
	>
		<template #footer>
			<el-button @click="addOrUpdateHandle(false)">新增</el-button>
			<el-button @click="refreshDataList()">刷新</el-button>
		</template>
	</ma-data-select>

	<UnitSingleAddOrUpdate ref="addOrUpdateRef" @refresh-data-list="refreshDataList"></UnitSingleAddOrUpdate>
</template>

<script setup lang="ts" name="GrSingleUnitInput">
import { reactive, ref, watch } from 'vue'

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

const emit = defineEmits(['change'])

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
