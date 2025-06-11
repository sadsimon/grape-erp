<template>
	<ma-data-select
		ref="maDataSelectRef"
		v-model="model"
		:url="'/product/grStore/listOfEffective'"
		:props="{ label: 'storeName', value: 'id' }"
		:placeholder
		filterable
		:style="{ width: width }"
		:disabled
		@select="select"
	>
		<template #footer>
			<el-button @click="addOrUpdateHandle(false)">新增</el-button>
			<el-button @click="refreshDataList()">刷新</el-button>
		</template>
	</ma-data-select>
	<StoreAddOrUpdate ref="addOrUpdateRef" @refresh-data-list="refreshDataList"></StoreAddOrUpdate>
</template>

<script setup lang="ts" name="GrStoreInput">
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
	},
	disabled: {
		type: Boolean,
		required: false,
		default: () => false
	},
})
const model = defineModel<number | string>()
const emit = defineEmits(['select'])

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
const select = (value: any)=>{
	emit('select',value)
}
</script>

<style></style>
