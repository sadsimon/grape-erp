<template>	
		<GrSelectableTable :height="listHeight" ref="tableRef" :initial-data="tableData" :fields="fields" :summaries="showSummaries">
			<template #columns>
				<el-table-column fixed="left" label="操作" align="center" width="100" v-if="!isfinish">
					<template #default="scope">
						<el-space :size="15">
							<ma-icon icon="icon-insert" style="cursor: pointer"
								@click="tableRef?.addRow(scope.$index)"></ma-icon>
							<ma-icon icon="icon-line" style="cursor: pointer"
								@click="tableRef?.removeRow(scope.$index)"></ma-icon>
						</el-space>
					</template>
				</el-table-column>
				<el-table-column prop="documentCode" align="center" width="200px" label="单据编号">
					<template #default="scope">
						<DocumentInput :disabled="isfinish" to-teleport="#documentList-dialog-container" :clearable="false"
							:multiple="scope.$index === tableData.length - 1" @select="check(scope.$index, $event)"
							:key="scope.row['id'] + ''" :inputValue="scope.row['documentCode']"></DocumentInput>
					</template>
				</el-table-column>
				<ma-dict-column prop="documentType" label="单据类型" dict-type="document_type" />
				<el-table-column class-name="el-table-column-text" prop="documentTime" align="center" width="140px" label="录单时间">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.documentTime }}</div>
					</template>
				</el-table-column>
				<el-table-column class-name="el-table-column-text" prop="contactunitsName" align="center" width="160px" label="结算单位名称">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.contactunitsName }}</div>
					</template>
				</el-table-column>
				<el-table-column class-name="el-table-column-text" prop="contactunitsCode" align="center" width="160px" label="结算单位编号">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.contactunitsCode }}</div>
					</template>
				</el-table-column>
				<el-table-column class-name="el-table-column-text" prop="realName" align="center" label="经手人">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.realName }}</div>
					</template>
				</el-table-column>
				<!-- <el-table-column class-name="el-table-column-text" prop="remark" align="center" label="说明">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.remark }}</div>
					</template>
				</el-table-column> -->
				<el-table-column class-name="el-table-column-text" prop="finalAmount" align="center" label="金额">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.finalAmount }}</div>
					</template>
				</el-table-column>
				<el-table-column fixed="right" class-name="el-table-column-text" prop="pendingAmount" align="center" label="待结算金额">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.pendingAmount }}</div>
					</template>
				</el-table-column>
				<el-table-column fixed="right" class-name="el-table-column-text" prop="paymentAmount" align="center" label="付款金额">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['paymentAmount']"
							@input="changeSumAmount(scope.$index)"></gr-number-input>
					</template>
				</el-table-column>
				<el-table-column fixed="right" class-name="el-table-column-text" prop="advanceAmount" align="center" label="预付款金额">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['advanceAmount']"
							@input="changeSumAmount(scope.$index)"></gr-number-input>
					</template>
				</el-table-column>
				<el-table-column fixed="right" class-name="el-table-column-text" prop="discountAmount" align="center" label="优惠">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['discountAmount']"
							@input="changeSumAmount(scope.$index)"></gr-number-input>
					</template>
				</el-table-column>
				<el-table-column fixed="right" class-name="el-table-column-text" prop="sumAmount" align="center" label="合计结算">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.sumAmount }}</div>
					</template>
				</el-table-column>
				
			</template>
		</GrSelectableTable>
</template>

<script setup lang="ts">
	import { reactive, ref, onMounted, watch } from 'vue'
	import { ElMessage } from 'element-plus/es'
	import { documentDataInt } from '@/views/document/settlement/settlement'
	import DocumentInput from '.././documentInput/index.vue'

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
			type: Array as () => documentDataInt[],
			default: () => [] as documentDataInt[]
		},
		isfinish: {
			type: Boolean,
			default: () => false
		},
		height: {
			type: Number,
			default: 500
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
		{ prop: 'pendingAmount', decimal: 2 },
		{ prop: 'paymentAmount', decimal: 2 },
		{ prop: 'advanceAmount', decimal: 2 },
		{ prop: 'discountAmount', decimal: 2 },
		{ prop: 'sumAmount', decimal: 2 }
	])

	const fields = ref(['id', 'documentCode', 'documentType', 'documentTime', 
	'contactunitsName', 'contactunitsCode', 'realName', 'remark', 'finalAmount', 
	'pendingAmount','paymentAmount','advanceAmount','discountAmount','sumAmount'])

	const check = (index : number, rows : documentDataInt[]) => {
		if (tableData.value.length === index + 1) {
			tableData.value.splice(index, 1)
			rows.forEach(row => {
				tableData.value.push(row)
			})
		} else {
			tableData.value[index] = rows[0]
		}
		emit('update:initialData', tableData.value)
	}

	const computedSumAmount = (row : documentDataInt) => {
		row['sumAmount'] = (row['paymentAmount'] || 0) + (row['advanceAmount'] || 0) + (row['discountAmount'] || 0)
	}

	const changeSumAmount = (index : number) => {
		tableData.value[index]['sumAmount'] = Number(tableData.value[index]['paymentAmount'])
		+ Number(tableData.value[index]['advanceAmount']) + Number(tableData.value[index]['discountAmount'])
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
	:deep(.el-input-group__append){
		padding: 0 8px !important;
	}
</style>