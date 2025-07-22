<template>
		<GrSelectableTable  :height="listHeight" ref="tableRef" :initial-data="tableData" :fields="fields" :summaries="showSummaries">
			<template #columns>
				<el-table-column label="操作" align="center" width="100" v-if="!isfinish">
					<template #default="scope">
						<el-space :size="15">
							<ma-icon icon="icon-insert" style="cursor: pointer"
								@click="tableRef?.addRow(scope.$index)"></ma-icon>
							<ma-icon icon="icon-line" style="cursor: pointer"
								@click="tableRef?.removeRow(scope.$index)"></ma-icon>
						</el-space>
					</template>
				</el-table-column>
				<el-table-column prop="storeId" align="center" label="仓库名称">
					<template #default="scope">
						<GrStoreInput :disabled="isfinish" v-model="scope.row['storeId']" @select ="getCurrentStock(scope.$index)" readonly></GrStoreInput>
					</template>
				</el-table-column>
				<el-table-column prop="barcode" align="center" label="包装条码">
					<template #default="scope">
						<GrProductInput :disabled="isfinish" to-teleport="#dialog-container" :clearable="false"
							:multiple="scope.$index === tableData.length - 1" @select="check(scope.$index, $event)"
							:key="scope.row['id'] + ''" :inputValue="scope.row['barcode']"></GrProductInput>
					</template>
				</el-table-column>
				<el-table-column prop="number" align="center" label="商品编码">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.number }}</div>
					</template>
				</el-table-column>
				<el-table-column prop="name" align="center" label="商品名称">
					<template #default="{ row }">
						<div class="text-with-border">{{ row.name }}</div>
					</template>
				</el-table-column>
				<el-table-column prop="stockCount" align="center" label="库存">
					<template #default="{ row }">
						<div class="text-with-border">{{ row.stockCount }}</div>
					</template>
				</el-table-column>
				<el-table-column class-name="el-table-column-text" prop="specs" align="center" label="规格">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.specs }}</div>
					</template>
				</el-table-column>
				<el-table-column class-name="el-table-column-text" prop="unitName" align="center" label="单位">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.unitName }}</div>
					</template>
				</el-table-column>
				<el-table-column prop="quantity" align="center" label="数量">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['quantity']"
							@input="changeQuantity(scope.$index)"></gr-number-input>
					</template>
				</el-table-column>
				<el-table-column prop="unitPrice" align="center" label="单价">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['unitPrice']"
							@input="changePurchasePrice(scope.$index)"></gr-number-input>
					</template>
				</el-table-column>
				<el-table-column prop="amount" align="center" label="金额">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['amount']"
							@input="changeAmount(scope.$index)"></gr-number-input>
					</template>
				</el-table-column>
				<el-table-column prop="remark" align="center" label="备注">
					<template #default="scope">
						<el-input :disabled="isfinish" v-model="scope.row['remark']" />
					</template>
				</el-table-column>
			</template>
		</GrSelectableTable>
		<!-- 弹框挂载点 -->
		<div id="dialog-container"></div>
</template>

<script setup lang="ts">
	import { reactive, ref, onMounted, watch } from 'vue'
	import { useBarcodeApi } from '@/api/product/product'
	import { useCurrentStock } from '@/api/product/store'
	import { ElMessage } from 'element-plus/es'
	import { DocumentDetail } from '@/views/document/index'
	import { cloneDeep } from 'lodash-es'

	const barcodeQuery = ref()
	

	interface SelectableTableMethods {
		addRow : (index : number) => void
		removeRow : (index : number) => void
	}

	interface SummaryConfig {
		prop : string // 字段名
		decimal : number // 小数位数
	}
	// 获取公共组件的实例
	const tableRef = ref<SelectableTableMethods | null>(null)

	const props = defineProps({
		initialData: {
			type: Array as () => DocumentDetail[],
			default: () => [] as DocumentDetail[]
		},
		isfinish: {
			type: Boolean,
			default: () => false
		},
		//上下被占用高度，本组件高度=窗口高度-占用高度
		height: {
			type: Number,
			default: 200
		}
	})

	const isfinish = ref(props.isfinish)

// 初始数据
	const tableData = ref(props.initialData)
	
	watch(
		() => props.isfinish,
		newVal => {
			isfinish.value = newVal
		}
	)
	
	watch(
		() => props.initialData,
		newVal => {
			tableData.value = newVal
		}
	)

	const emit = defineEmits(['update:initialData'])

	

	//合计行
	const showSummaries = ref<SummaryConfig[]>([
		{ prop: 'quantity', decimal: 0 },
		{ prop: 'amount', decimal: 2 }
	])

	const fields = ref(['id', 'storeId', 'number', 'name', 'barcode', 'specs', 'quantity', 'unitId', 'unitPrice', 'amount'])

	const check = (index : number, rows : DocumentDetail[]) => {
		if (tableData.value.length === index + 1) {
			tableData.value.splice(index, 1)
			rows.forEach(row => {
				row['unitPrice'] = row['expectPurchasePrice']
				row['quantity'] = 1
				tableData.value.push(row)
			})
			tableRef.value?.addRow(index + 1)
		} else {
			rows[0]['unitPrice'] = rows[0]['expectPurchasePrice']
			rows[0]['quantity'] = 1
			tableData.value[index] = rows[0]
		}
		getCurrentStock(index)
		computedAmounts(rows)
		emit('update:initialData', tableData)
	}

	const checkByBarcode = (barcodeQuery: string) => {
		useBarcodeApi(barcodeQuery).then(res => {
			if (res.data === null) {
				ElMessage.warning('未查询到数据')
			} else {
				const existingIndex = tableData.value.findIndex(item => item.id === res.data.id) // 找到元素的索引
				if (tableData.value[existingIndex]) {
					tableData.value[existingIndex].quantity = Number(1 + Number(tableData.value[existingIndex].quantity))
				} else {
					if (tableData.value[tableData.value.length - 1]['id'] === null) {
						tableData.value.splice(tableData.value.length - 1, 1)
					}
					res.data['quantity'] = 1
					tableData.value.push(res.data)
					tableRef.value?.addRow(tableData.value.length)
				}
				computedAmount(tableData.value[existingIndex])
				emit('update:initialData', tableData)
			}
		})
	}

	const computedAmounts = (rows : DocumentDetail[]) => {
		rows.forEach(row => {
			if (row['unitPrice']) {
				row['amount'] = row['quantity']?row['quantity'] * row['unitPrice'] : 0
			}
		})
	}

	const computedAmount = (row : DocumentDetail) => {
		if (row['unitPrice']) {
			row['amount'] = row['quantity']?row['quantity'] * row['unitPrice'] : 0
		}
	}

	const changeQuantity = (index : number) => {
		const row = tableData.value[index];
		  if (row['quantity'] === null || row['quantity'] === undefined || row['unitPrice'] === null || row['unitPrice'] === undefined) {
		    row['amount'] = 0;
		  } else {
		    row['amount'] = row['quantity'] * row['unitPrice'];
		  }
	}
	const changePurchasePrice = (index : number) => {
		const row = tableData.value[index];
		  if (row['quantity'] === null || row['quantity'] === undefined || row['unitPrice'] === null || row['unitPrice'] === undefined) {
		    row['amount'] = 0;
		  } else {
		    row['amount'] = row['quantity'] * row['unitPrice'];
		  }
	}
	const changeAmount = (index : number) => {
		const row = tableData.value[index];
		  if (row['amount'] === null || row['amount'] === undefined || row['quantity'] === null || row['quantity'] === undefined) {
		    row['unitPrice'] = 0;
		  } else {
		    row['unitPrice'] = Number((row['amount'] / row['quantity']).toFixed(2))
		  }
	}
	
	 //计算高度
	const listHeight = ref(0)
	watch(
		() => props.height,
		newVal => {
			listHeight.value = Number(newVal)
		}
	)
	
	const getCurrentStock = (index : number) =>{
		useCurrentStock(tableData.value[index]['productId'],tableData.value[index]['storeId']).then((res: any)=>{
			tableData.value[index]['stockCount'] = res.data
		})
	}
	
	const refresh=()=>{
		tableData.value.forEach((row: DocumentDetail)=>{
			useCurrentStock(row['productId'],row['storeId']).then((res: any)=>{
				row['stockCount'] = res.data
			})
		})
	}
	defineExpose({
		refresh,checkByBarcode
	})
</script>

<style scoped>

</style>