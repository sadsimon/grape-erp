<template>
	<GrDialog v-model="visible" :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" draggable>
		<el-form ref="dataFormRef" :model="dataForm" :rules="dataRules" label-width="100px">
			<el-row :gutter="30">
				<el-col :span="24" :lg="12" :md="12" :sm="24">
					<el-form-item prop="type" label="类型">
						<el-radio-group v-model="dataForm.type">
						      <el-radio value="0" >供应商</el-radio>
						      <el-radio value="1" >客户</el-radio>
						    </el-radio-group>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="12" :md="12" :sm="24">
					<el-form-item prop="contactunitsCode" label="编号">
						<el-input v-model="dataForm.contactunitsCode"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="12" :md="12" :sm="24">
					<el-form-item prop="contactunitsName" label="名称">
						<el-input v-model="dataForm.contactunitsName"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="12" :md="12" :sm="24">
					<el-form-item prop="categoryId" label="类别">
						<ma-data-tree-select
							v-model="dataForm.categoryId"
							url="/product/grContactunitsCategory/list"
							:props="{ label: 'categoryName', children: 'children' }"
						></ma-data-tree-select>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="12" :md="12" :sm="24">
					<el-form-item prop="initialAdvancePayment" label="期初预付款">
						<el-input v-model="dataForm.initialAdvancePayment"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="12" :md="12" :sm="24">
					<el-form-item prop="initialPayable" label="期初应付">
						<el-input v-model="dataForm.initialPayable"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="12" :md="12" :sm="24">
					<el-form-item prop="taxpayerIdNum" label="纳税人识别号">
						<el-input v-model="dataForm.taxpayerIdNum"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="12" :md="12" :sm="24">
					<el-form-item prop="rate" label="增值税率%">
						<el-input v-model="dataForm.rate"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="12" :md="12" :sm="24">
					<el-form-item prop="depositBank" label="开户银行">
						<el-input v-model="dataForm.depositBank"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="12" :md="12" :sm="24">
					<el-form-item prop="bankNumber" label="银行账号">
						<el-input v-model="dataForm.bankNumber"></el-input>
					</el-form-item>
				</el-col>
			</el-row>
			<gr-data-table-edit
				:headers="headers"
				delete-url="/product/grContactunitsContact"
				:initial-data="dataForm.contactList"
				:show-summary="showSummary"
				@selected="showInfo"
				@table-data-change="handleTableDataChange"
			>
			</gr-data-table-edit>
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="submitHandle()">确定</el-button>
		</template>
	</GrDialog>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus/es'
import { useContactunitsSubmitApi, useContactunitsApi } from '@/api/product/contactunits'

const innerVisible = ref(false)

const showInfo = () => {
	console.log('=======================================')
}

// 控制搜索页的显示和隐藏
const showSummary = ref(false)
// 表头配置
const headers = ref([
	{
		prop: 'contactName',
		label: '联系人',
		type: 'text',
		showSummary: false
	},
	{
		prop: 'mobile',
		label: '手机号',
		type: 'text',
		showSummary: false
	},
	{
		prop: 'landline',
		label: '座机',
		type: 'text',
		showSummary: false
	},
	{
		prop: 'netContact',
		label: 'QQ/微信/Email',
		type: 'text',
		showSummary: false
	},
	{
		prop: 'address',
		label: '联系地址',
		type: 'address',
		showSummary: false,
		width: '300'
	}
])

const emit = defineEmits(['refreshDataList'])

const visible = ref(false)
const dataFormRef = ref()
const dataForm = reactive({
	id: '',
	type: '',
	contactunitsCode: '',
	contactunitsName: '',
	categoryId: '',
	salesmanId: '',
	depositBank: '',
	bankNumber: '',
	taxpayerIdNum: '',
	initialAdvancePayment: '',
	initialPayable: '',
	rate: '',
	contactList: [] as any[]
})

const dataRules = ref({
	contactunitsCode: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
	contactunitsName: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
	type: [{ required: true, message: '必填项不能为空', trigger: 'blur' }]
})

const init = (id?: number) => {
	visible.value = true
	dataForm.id = ''

	// 重置表单数据
	if (dataFormRef.value) {
		dataFormRef.value.resetFields()
	}

	// 初始化 contactList
	dataForm.contactList = [] // 明确设置为空数组

	// id 存在则为修改
	if (id) {
		getContactunits(id)
	}
}

const getContactunits = (id: number) => {
	useContactunitsApi(id).then(res => {
		Object.assign(dataForm, res.data)
	})
}

// 接收子组件传递的 tableData
const handleTableDataChange = (newData: any[]) => {
	dataForm.contactList = newData
}

// 表单提交
const submitHandle = () => {
	dataFormRef.value.validate((valid: boolean) => {
		if (!valid) {
			return false
		}

		useContactunitsSubmitApi(dataForm).then(() => {
			ElMessage.success({
				message: '操作成功',
				duration: 500,
				onClose: () => {
					visible.value = false
					emit('refreshDataList')
				}
			})
		})
	})
}

defineExpose({
	init
})
</script>
