<template>
	<el-card>
		<!-- 表格 -->
		<el-table :data="tableData" style="width: 100%">
			<el-table-column prop="unitName" label="计量单位" header-align="center" align="center" />
			<el-table-column prop="formula" label="换算公式" header-align="center" align="center" />
			<el-table-column prop="barcode" label="包装条码">
				<template #default="{ row }">
					<el-input v-model="row['barcode']"></el-input>
				</template>
			</el-table-column>
			<el-table-column prop="retailPrice" label="零售价">
				<template #default="{ row }">
					<el-input v-model="row['retailPrice']"></el-input>
				</template>
			</el-table-column>

			<el-table-column prop="wholesalePrice" label="批发价">
				<template #default="{ row }">
					<el-input v-model="row['wholesalePrice']"></el-input>
				</template>
			</el-table-column>

			<el-table-column prop="membershipPrice" label="会员价">
				<template #default="{ row }">
					<el-input v-model="row['membershipPrice']"></el-input>
				</template>
			</el-table-column>

			<el-table-column prop="discountRate" label="折扣率(%)">
				<template #default="{ row }">
					<el-input v-model="row['discountRate']"></el-input>
				</template>
			</el-table-column>

			<el-table-column prop="lowestSellingPrice" label="最低售价">
				<template #default="{ row }">
					<el-input v-model="row['lowestSellingPrice']"></el-input>
				</template>
			</el-table-column>

			<el-table-column prop="expectPurchasePrice" label="预计采购价">
				<template #default="{ row }">
					<el-input v-model="row['expectPurchasePrice']"></el-input>
				</template>
			</el-table-column>

			<el-table-column prop="highestPurchasePrice" label="最高采购价">
				<template #default="{ row }">
					<el-input v-model="row['highestPurchasePrice']"></el-input>
				</template>
			</el-table-column>
		</el-table>
	</el-card>
</template>

<script setup lang="ts">
import { reactive, ref, watch } from 'vue'

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
/* 使用 ::v-deep 深度选择器，修改table之间的间距 */
::v-deep .el-table--default .cell {
	padding: 0 3px !important; /* 将 padding 调整为 0 3px */
}
</style>
