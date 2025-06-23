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
				<el-table-column prop="accountName" align="center" label="账户名称">
					<template #default="scope">
						<GrSettlementAccountInput :disabled="isfinish" @select="check(scope.$index, $event)" v-model="scope.row['accountName']"></GrSettlementAccountInput>
					</template>
				</el-table-column>
				<el-table-column prop="accountCode" align="center" label="账户编号">
					<template #default="scope">
						<div class="text-with-border">{{ scope.row.accountCode }}</div>
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
	import { DocumentAccountDetailInt } from '@/views/document/settlement/settlement'

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
			type: Array as () => DocumentAccountDetailInt[],
			default: () => [] as DocumentAccountDetailInt[]
		},
		isfinish: {
			type: Boolean,
			default: () => false
		},
		height: {
			type: Number,
			default: 0
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

	const check = (index : number, data: object) => {
		tableData.value[index]['accountCode'] = (data as any)['accountCode']
		tableData.value[index]['accountId'] = (data as any)['id']
		emit('update:initialData', tableData.value)
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