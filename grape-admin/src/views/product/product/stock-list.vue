<template>
	<el-card style="max-width: 600px">
		<el-table :data="tableData">
			<el-table-column prop="storeName" align="center" label="仓库">
				<template #default="{ row }">
					{{ row['storeName'] }}
				</template>
			</el-table-column>
			<el-table-column prop="stockCount" align="center" label="期初库存数量">
				<template #default="{ row }">
					<gr-number-input v-model="row['stockCount']"></gr-number-input>
				</template>
			</el-table-column>
			<el-table-column prop="minStockCount" align="center" label="最低安全库存数量">
				<template #default="{ row }">
					<gr-number-input v-model="row['minStockCount']"></gr-number-input>
				</template>
			</el-table-column>
			<el-table-column prop="maxStockCount" align="center" label="最高安全库存数量">
				<template #default="{ row }">
					<gr-number-input v-model="row['maxStockCount']"></gr-number-input>
				</template>
			</el-table-column>
		</el-table>
	</el-card>
</template>

<script setup lang="ts">
import { reactive, ref, watch, onMounted } from 'vue'
import { useStoreListApi } from '@/api/product/store'

const props = defineProps({
	initialData: {
		type: Array as () => Array<{ [key: string]: any }>,
		default: () => []
	}
})

// 表格数据
const tableData = ref([...props.initialData])

watch(
	() => props.initialData,
	newValue => {
		console.log('接收到的初始数据:', newValue)
		tableData.value = newValue
	}
)
</script>

<style scoped>
:v-deep (.el-table--default .cell) {
	padding: 0 3px !important; /* 将 padding 调整为 0 3px */
}
</style>
