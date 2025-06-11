<template>
	<ma-data-select
		ref="maDataSelectRef"
		v-model="model"
		:url="url"
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
	<IncomeExpendAddOrUpdate :to-teleport="toTeleport" ref="addOrUpdateRef" @refresh-data-list="refreshDataList"></IncomeExpendAddOrUpdate>
</template>

<script setup lang="ts" name="GrIncomeExpendInput">
import { reactive, ref } from 'vue'
interface MaDataSelect {
	refresh: () => void
}
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
	},
	url: {
		type: String,
		required: true,
		default: '/product/grIncomeExpend/listOfEffective'
	},
	toTeleport: {
		type: String,
		required: false
	}
})
const maDataSelectRef = ref<MaDataSelect | null>(null) // MaDataSelect 的 ref
const model = defineModel<number | string>()

const addOrUpdateRef = ref()
const addOrUpdateHandle = (isUpdate: Boolean, row?: any) => {
	addOrUpdateRef.value.init(isUpdate, row)
}

const refreshDataList = () => {
	if (maDataSelectRef.value) {
		maDataSelectRef.value.refresh()
	} else {
		console.error('maDataSelectRef.value is null')
	}
}
</script>

<style></style>
