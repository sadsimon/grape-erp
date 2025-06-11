<template>
	<div>
		<!-- 操作按钮 -->
		<div v-if="showActionButtons" style="margin-bottom: 10px">
			<el-button icon="Plus" type="primary" @click="addRow">添加一行</el-button>
		</div>
		<!-- 表格 -->
		<el-table :data="tableData" style="width: 100%" :show-summary="showSummary" :summary-method="getSummaries">
			<el-table-column v-for="(header, index) in headers" :key="index" :prop="header.prop" :label="header.label" :width="header.width">
				<template #default="{ row, $index }">
					<!-- 文本输入 -->
					<el-input v-if="header.type === 'text'" v-model="row[header.prop]" @change="handleCellChange($index, header.prop, row[header.prop])" />
					<!-- 下拉选项 -->
					<el-select v-else-if="header.type === 'select'" v-model="row[header.prop]" @click="handleCellChange($index, header.prop, row[header.prop])">
						<el-option v-for="option in header.options" :key="option.value" :label="option.label" :value="option.value" />
					</el-select>
					<ma-dict-select
						v-if="header.type === 'user_gender'"
						label="性别"
						dict-type="user_gender"
						@click="handleCellChange($index, header.prop, row[header.prop])"
					></ma-dict-select>
					<!-- 弹框选项 -->
					<gr-address-input
						v-if="header.type === 'address'"
						:province="row['province']"
						:city="row['city']"
						:district="row['district']"
						:address="row['address']"
						@submit="getAddress($index, $event)"
						@click="handleCellChange($index, header.prop, row[header.prop])"
					/>
				</template>
			</el-table-column>
			<el-table-column v-if="showActionColumn" label="操作">
				<template #default="{ row, $index }">
					<el-button type="danger" @click="removeRow($index, row.id)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script setup lang="ts" name="GrDataTableEdit">
import { ref, watch } from 'vue'
import { ElMessage } from 'element-plus'
import { deleteHandle } from '@/api/common'

const emit = defineEmits(['tableDataChange'])

// 定义 props
const props = defineProps({
	headers: {
		type: Array as () => Array<{
			prop: string
			label: string
			type: 'text' | 'select' | 'custom-search' | 'product' | 'address' | 'user_gender'
			showSummary?: boolean
			options?: Array<{ label: string; value: any; [key: string]: any }>
			fetchOptions?: () => Promise<any>
			mapFields?: { [key: string]: string }
			width: string
		}>,
		required: true
	},
	initialData: {
		type: Array as () => Array<{ [key: string]: any }>,
		default: () => []
	},
	showActionColumn: {
		type: Boolean,
		default: true
	},
	showActionButtons: {
		type: Boolean,
		default: true
	},
	showSummary: {
		type: Boolean,
		default: false
	},
	deleteUrl: {
		type: String,
		default: ''
	}
})

// 表格数据
const tableData = ref([...props.initialData])

// 单元格内容变化
const handleCellChange = (rowIndex: number, prop: string, value: any) => {
	console.log(`第 ${rowIndex + 1} 行, ${prop} 修改为: ${value}`)
	emit('tableDataChange', tableData.value)
}

//获取子组件的address数据
const getAddress = (rowIndex: number, eventData: any) => {
	const { province, city, district, address } = eventData
	tableData.value[rowIndex]['province'] = province
	tableData.value[rowIndex]['city'] = city
	tableData.value[rowIndex]['district'] = district
	tableData.value[rowIndex]['address'] = address
}
watch(
	() => props.initialData,
	newValue => {
		console.log('接收到的初始数据:', newValue)
		tableData.value = newValue
	}
)

// 合计行逻辑
const getSummaries = (param: { columns: any[]; data: any[] }) => {
	const { columns, data } = param
	const sums: string[] = []
	columns.forEach((column, index) => {
		if (index === 0) {
			sums[index] = '合计'
			return
		}
		const header = props.headers.find(h => h.prop === column.property)
		if (header?.showSummary) {
			const values = data.map(item => Number(item[column.property]))
			if (!values.every(value => isNaN(value))) {
				sums[index] = values
					.reduce((prev, curr) => {
						const value = Number(curr)
						if (!isNaN(value)) {
							return prev + curr
						} else {
							return prev
						}
					}, 0)
					.toString()
			} else {
				sums[index] = 'N/A'
			}
		} else {
			sums[index] = ''
		}
	})
	return sums
}

// 添加一行
const addRow = () => {
	const newRow: { [key: string]: any } = {}
	props.headers.forEach(header => {
		newRow[header.prop] = ''
	})
	tableData.value.push(newRow)
	emit('tableDataChange', tableData.value)
}

//删除一行
const removeRow = (index: number, id: any) => {
	if (id) {
		// 调用公共删除方法，并传递回调函数
		deleteHandle(id, props.deleteUrl, () => {
			// 删除操作成功后的业务逻辑
			tableData.value.splice(index, 1)
			emit('tableDataChange', tableData.value)
		})
	} else {
		tableData.value.splice(index, 1)
		emit('tableDataChange', tableData.value)
	}
}
</script>

<style scoped>
/* 使用 ::v-deep 深度选择器，修改table之间的间距 */
::v-deep .el-table--default .cell {
	padding: 0 3px !important; /* 将 padding 调整为 0 3px */
}
</style>
