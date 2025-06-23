<template>	
		<GrSelectableTable :height="listHeight" ref="tableRef" :initial-data="tableData" :fields="fields" :summaries="showSummaries">
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
				<el-table-column prop="projectId" align="center" label="项目名称">
					<template #default="scope">
						<GrIncomeExpendInput :url="'/product/grIncomeExpend/listOfEffective/'+amountType" v-model="scope.row['projectId']" to-teleport="#documentList-dialog-container" ></GrIncomeExpendInput>
					</template>
				</el-table-column>
				<el-table-column prop="amount" align="center" label="金额">
					<template #default="scope">
						<gr-number-input :disabled="isfinish" v-model="scope.row['amount']"></gr-number-input>
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
	import { ElMessage } from 'element-plus/es'
	import { DocumentDetail } from '@/views/document/index'

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
		height: {
			type: Number,
			default: 0
		},
		amountType: {
			type: String
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
		{ prop: 'amount', decimal: 2 }
	])

	const fields = ref(['id', 'accountCode', 'accountName', 'paymentAmount', 'remark'])
	
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