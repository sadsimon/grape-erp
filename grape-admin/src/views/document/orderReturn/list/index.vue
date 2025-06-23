<template>
		<el-space>
			<el-space>
				<el-input :disabled="isfinish" v-model="barcodeQuery" style="width: 350px" placeholder="扫码枪精准匹配"
					@keydown.enter="checkByBarcode">
					<template #prepend>条码</template>
				</el-input>
				<GrField />
			</el-space>
		</el-space>
			
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
				<el-table-column prop="storeId" align="center" label="收货仓库" min-width="120px">
					<template #default="scope">
						<GrStoreInput :disabled="isfinish" v-model="scope.row['storeId']" readonly></GrStoreInput>
					</template>
				</el-table-column>
				<el-table-column prop="barcode" align="center" label="包装条码" min-width="130px">
					<template #default="scope">
						<GrProductInput :disabled="isfinish" to-teleport="#dialog-container" :clearable="false"
							:multiple="scope.$index === tableData.length - 1" @select="check(scope.$index, $event)"
							:key="scope.row['id'] + ''" :inputValue="scope.row['barcode']"></GrProductInput>
					</template>
				</el-table-column>
				<el-table-column class-name="el-table-column-text" prop="unitName" align="center" label="单位">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.unitName }}</div>
					</template>
				</el-table-column>
				<el-table-column prop="productNumber" align="center" label="商品编码">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.productNumber }}</div>
					</template>
				</el-table-column>
				<el-table-column prop="productName" align="center" label="商品名称">
					<template #default="{ row }">
						<div class="text-with-border">{{ row.productName }}</div>
					</template>
				</el-table-column>
				<el-table-column class-name="el-table-column-text" prop="specs" align="center" label="规格">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.specs }}</div>
					</template>
				</el-table-column>
				<el-table-column prop="quantity" align="center" label="数量">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['quantity']"
							@input="changeQuantity(scope.$index)"></gr-number-input>
					</template>
				</el-table-column>
				<el-table-column prop="unitPrice" align="center" label="采购价">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['unitPrice']"
							@input="changeUnitPrice(scope.$index)"></gr-number-input>
					</template>
				</el-table-column>
				<el-table-column prop="amount" align="center" label="金额">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['amount']"
							@input="changeAmount(scope.$index)"></gr-number-input>
					</template>
				</el-table-column>
				<el-table-column prop="taxRate" align="center" label="税率">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['taxRate']" placeholder="%"
							@input="changeAmount(scope.$index)"></gr-number-input>
					</template>
				</el-table-column>
				<el-table-column prop="taxAmount" align="center" label="税额">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['taxAmount']"
							@input="changeAmount(scope.$index)"></gr-number-input>
					</template>
				</el-table-column>
				<el-table-column prop="finalAmount" align="center" label="税价合计">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['finalAmount']"
							@input="changeAmount(scope.$index)"></gr-number-input>
					</template>
				</el-table-column>
				<el-table-column prop="remark" align="center" label="备注">
					<template #default="scope">
						<el-input
						    v-model="scope.row['remark']"
						  />
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
		{ prop: 'amount', decimal: 2 },
		{ prop: 'taxAmount', decimal: 2 },
		{ prop: 'finalAmount', decimal: 2 },
	])

	const fields = ref(['id', 'storeId', 'number', 'name', 'barcode', 'specs', 'quantity', 'unitId', 
	'unitPrice', 'amount', 'taxRate', 'taxAmount', 'finalAmount', 'remark'])

	const check = (index : number, rows : DocumentDetail[]) => {
		if (tableData.value.length === index + 1) {
			tableData.value.splice(index, 1)
			rows.forEach(row => {
				row['unitPrice'] = row['expectPurchasePrice']
				row['quantity'] = 1
				tableData.value.push(row)
			})
		} else {
			rows[0]['unitPrice'] = rows[0]['expectPurchasePrice']
			rows[0]['quantity'] = 1
			tableData.value[index] = rows[0]
		}
		computedAmounts(rows)
		emit('update:initialData', tableData)
	}

	const checkByBarcode = () => {
		useBarcodeApi(barcodeQuery.value).then(res => {
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
				}
				computedAmount(tableData.value[existingIndex])
				emit('update:initialData', tableData)
			}
			barcodeQuery.value = null
		})
	}

	const computedAmounts = (rows : DocumentDetail[]) => {
		rows.forEach(row => {
			if (row['unitPrice']) {
				row['amount'] = row['quantity']?row['quantity'] * row['unitPrice'] : 0
			}
		})
	}


	//修改数量
	const changeQuantity = (index : number) => {
		const row = tableData.value[index]
		computedAmount(row)
		computedTaxAmount(row)
		computedFinalAmount(row)
	}
	
	//修改单价
	const changeUnitPrice = (index : number) => {
		const row = tableData.value[index]
		computedAmount(row)
		computedTaxAmount(row)
		computedFinalAmount(row)
	}
	
	//修改金额
	const changeAmount = (index : number) => {
		const row = tableData.value[index]
		computedUnitPrice(row)
		computedTaxAmount(row)
		computedFinalAmount(row)
	}
	
	//单价（采购价）
	const computedUnitPrice = (row : DocumentDetail) => {
		if(row['quantity']){
			row['unitPrice'] = Number((row['amount']?row['amount'] / row['quantity'] : 0).toFixed(2))
		}
	}
	
	//金额
	const computedAmount = (row : DocumentDetail) => {
		if (row['unitPrice']) {
			row['amount'] = row['quantity']?row['quantity'] * row['unitPrice'] : 0
		}
	}
	
	//税额
	const computedTaxAmount = (row : DocumentDetail) => {
		if (row['amount']) {
			row['taxAmount'] = row['taxRate']? (row['taxRate']/100)* row['amount'] : 0
		}
	}
	
	//税价合计
	const computedFinalAmount = (row : DocumentDetail) => {
		if(row['amount']){
			row['finalAmount'] = row['taxRate']? row['amount'] * (1-(row['taxRate']/100)) : row['amount']
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
	
</script>

<style scoped>

</style>