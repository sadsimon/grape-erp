<template>
	<div>
		<el-table class="layout-table" border :height="tableHeight" :data="tableData" show-summary :summary-method="getSummaries">
			<slot name="columns"></slot>
		</el-table>
	</div>
</template>

<script setup lang="ts" name="GrSelectableTable">
import { reactive, ref, watch, onMounted, onUnmounted, computed } from 'vue'


const props = defineProps({
	initialData: {
		type: Array,
		default: () => []
	},
	fields: {
		type: Array as () => string[],
		default: () => []
	},
	summaries: {
		type: Array as () => SummaryConfig[],
		default: () => []
	},
	height: {
		type: Number,
		default: 500
	}
})


interface SummaryConfig {
	prop: string // 字段名
	decimal: number // 小数位数
}

// 初始化表格高度
const tableHeight = ref(0)

watch(
	() => props.height,
	newValue => {
		tableHeight.value = newValue
		console.log(tableHeight.value)
	}
)

// 表格数据
const tableData = ref([...props.initialData])

watch(
	() => props.initialData,
	newValue => {
		tableData.value = newValue
	},
	{ deep: true }
)

// 合计字段配置
const showSummaries = computed(() => props.summaries)
//const showSummaries = ref([{ prop: 'quantity', decimal: 0 }, { prop: 'amount', decimal: 2 }]);

// 合计行逻辑
const getSummaries = (param: { columns: any[]; data: any[] }) => {
	const { columns, data } = param
	const sums: any[] = []
	columns.forEach((column, index) => {
		if (index === 0) {
			sums[index] = '合计'
			return
		}
		const prop = column.property
		const summary = showSummaries.value.find(item => item.prop === prop)
		if (summary) {
			const values = data.map(item => Number(item[prop]))
			if (!values.every(value => isNaN(value))) {
				const sum = values.reduce((prev, curr) => {
					const value = Number(curr)
					if (!isNaN(value)) {
						return prev + value
					} else {
						return prev
					}
				}, 0)
				sums[index] = sum.toFixed(summary.decimal) // 根据指定的小数位数格式化合计值
			} else {
				sums[index] = 0
			}
		} else {
			sums[index] = ''
		}
	})
	return sums
}

// 动态生成新行
const createNewRow = () => {
	// 如果有字段定义，根据字段定义生成新行
	if (props.fields.length > 0) {
		const newRow = {} as Record<string, any>
		for (const field of props.fields) {
			newRow[field] = field === 'id' ? null : ''
		}
		return newRow
	}
	return {}
}

// 添加一行
const addRow = (index: number) => {
	const newRow = createNewRow()
	tableData.value.splice(index + 1, 0, newRow)
}

// 删除一行
const removeRow = (index: number) => {
	if (tableData.value.length === 1) {
		// 如果只有一行，清空数据而不是删除
		const newRow = createNewRow()
		tableData.value[0] = newRow
	} else {
		// 删除指定索引的行
		tableData.value.splice(index, 1)
	}
}

// 暴露方法供父组件调用
defineExpose({
	addRow,
	removeRow
})
</script>

<style scoped>
/* 使用 ::v-deep 深度选择器，修改table之间的间距 */
::v-deep .el-table--default .cell {
	padding: 0 3px !important; /* 将 padding 调整为 0 3px */
}

::v-deep .el-table .el-table__footer-wrapper tfoot tr td {
	background-color: #fdf7df; /* 设置合计行的背景色 */
	color: #333; /* 设置合计行的字体颜色 */
	text-align: right;
	padding-right: 10px;
}

/* 隐藏内部单元格的纵向边框 */
::v-deep .el-table__row > td {
	border-right: none !important; /* 右边框隐藏 */
	border-left: none !important; /* 左边框隐藏 */
}

/* 保留最外层的纵向边框 */
::v-deep .el-table__body-wrapper {
	border-left: 1px solid #ebeef5; /* 左边框 */
	border-right: 1px solid #ebeef5; /* 右边框 */
}

/* 隐藏表头的纵向边框 */
::v-deep .el-table__cell {
	border-left: none !important; /* 左边框隐藏 */
	border-right: none !important; /* 右边框隐藏 */
	padding: 3px 0 !important;
}
</style>
