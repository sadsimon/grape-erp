<template>
	<GrDialog v-model="visible" show-full="true" :title="!dataForm.id ? '新增商品' : '修改商品'" draggable>
		<el-divider content-position="left">基本信息</el-divider>
		<el-form ref="dataFormRef" :model="dataForm" :rules="dataRules" label-width="100px">
			<el-row :gutter="5">
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="productNumber" label="商品编码">
						<el-input v-model="dataForm.productNumber"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="productName" label="商品名称">
						<el-input v-model="dataForm.productName"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="barcode" label="商品条码">
						<el-input v-model="dataForm.barcode"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="specs" label="规格型号">
						<el-input v-model="dataForm.specs"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="categoryId" label="商品类别">
						<ma-data-tree-select
							v-model="dataForm.categoryId"
							url="/product/grProductCategory/list"
							:props="{ label: 'categoryName', children: 'children' }"
						></ma-data-tree-select>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="defaultStoreId" label="默认仓库">
						<gr-store-input v-model="dataForm.defaultStoreId"></gr-store-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="defaultContactunitsId" label="默认供应商">
						<GrContactunitsInput v-model="dataForm.defaultContactunitsId"></GrContactunitsInput>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item v-if="dataForm.model" label="多单位" prop="manayUnit">
						<GrMultipleUnitInput v-model="dataForm.unitId"></GrMultipleUnitInput>
					</el-form-item>
					<el-form-item v-else prop="unitId" label="计量单位">
						<gr-single-unit-input v-model="dataForm.unitId"></gr-single-unit-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item>
						<el-checkbox v-model="dataForm.model" label="多单位" size="large" @change="dataForm.unitId = ''" />
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="brandId" label="品牌">
						<GrBrandInput v-model="dataForm.brandId"></GrBrandInput>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="origin" label="产地">
						<el-input v-model="dataForm.origin"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="registCode" label="注册证号">
						<el-input v-model="dataForm.registCode"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="productionLicense" label="生产许可证">
						<el-input v-model="dataForm.productionLicense"></el-input>
					</el-form-item>
				</el-col>
				
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="plu" label="PLU码">
						<el-input v-model="dataForm.plu" placeholder="请输入1~99999数字"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="8" :md="12" :sm="24">
					<el-form-item prop="weight" label="重量(kg)">
						<el-input v-model="dataForm.weight"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="24" :md="24" :sm="24">
					<el-form-item prop="length_wide_tall" label="长宽高">
						长<el-input v-model="dataForm.length" style="margin-left: 2px; margin-right: 10px;width: 100px;"/>
						宽<el-input v-model="dataForm.wide" style="margin-left: 2px; margin-right: 10px;width: 100px;"/>
						高<el-input v-model="dataForm.tall" style="margin-left: 2px; margin-right: 10px;width: 100px;"/>
						<ma-dict-select v-model="dataForm.volumeUnit" dict-type="volume_unit" style="width: 65px;"></ma-dict-select>
					</el-form-item>
				</el-col>
				<el-col :span="24" :lg="24" :md="24" :sm="24">
					<el-form-item prop="remark" label="备注">
						<el-input v-model="dataForm.remark" type="textarea"></el-input>
					</el-form-item>
				</el-col>
			</el-row>
			<el-divider content-position="left">价格条码</el-divider>
			<BarcodeList :initial-data="dataForm.barcodeVOS"></BarcodeList>
			<el-divider content-position="left">期初设置</el-divider>
			<StockList :initial-data="dataForm.productStockVOS"></StockList>
			<el-divider content-position="left">图片</el-divider>
			<MaUploadImages v-model="dataForm.imgs" size="2" height="200px" width="200px"
				><template #tip>支持jpeg\png,最多上传4张,单张最大2M</template></MaUploadImages
			>
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="submitHandle()">确定</el-button>
		</template>
	</GrDialog>
</template>

<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import { ElMessage } from 'element-plus/es'
import { useProductApi, useProductSubmitApi, useUnitListApi } from '@/api/product/product'
import { useProductStockApi } from '@/api/product/store'

import BarcodeList from './barcode-list.vue'
import StockList from './stock-list.vue'

interface img {
	id?: number
	url: string
}
const emit = defineEmits(['refreshDataList'])
const imgUrls = ref()
const visible = ref(false)
const dataFormRef = ref()
const dataForm = reactive({
	id: '',
	number: '',
	name: '',
	barcode: '',
	specs: '',
	categoryId: '',
	defaultStoreId: '',
	defaultContactunitsId: '',
	unitId: '',
	manayUnitId: '',
	model: false,
	origin: '',
	registCode: '',
	productionLicense: '',
	brandId: '',
	plu: '',
	weight: '',
	length: '',
	wide: '',
	tall: '',
	volumeUnit: '',
	remark: '',
	barcodeVOS: [],
	productStockVOS: [],
	imgs: ''
})

const dataRules = ref({
	number: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
	name: [{ required: true, message: '必填项不能为空', trigger: 'blur' }]
})

// 标志位，用于控制监听器是否生效
let shouldListenToUnitIdChange = false

// 在组件初始化时绑定 watch
watch(
	() => dataForm.unitId,
	async newValue => {
		if (!shouldListenToUnitIdChange) {
			return
		} // 如果不应监听，则直接返回

		if (newValue) {
			const res = await useUnitListApi(Number(newValue))
			dataForm.barcodeVOS = res.data
		} else {
			dataForm.barcodeVOS = []
		}
	},
	{ immediate: false }
)

const init = async (isUpdate: boolean, id?: number) => {
	visible.value = true
	// 手动重置字段
	dataForm.id = ''
	dataForm.number = ''
	dataForm.name = ''
	dataForm.barcode = ''
	dataForm.specs = ''
	dataForm.categoryId = ''
	dataForm.defaultStoreId = ''
	dataForm.defaultContactunitsId = ''
	dataForm.unitId = ''
	dataForm.manayUnitId = ''
	dataForm.model = false
	dataForm.origin = ''
	dataForm.registCode = ''
	dataForm.productionLicense = ''
	dataForm.brandId = ''
	dataForm.plu = ''
	dataForm.weight = ''
	dataForm.length = ''
	dataForm.wide = ''
	dataForm.tall = ''
	dataForm.volumeUnit = ''
	dataForm.remark = ''
	dataForm.barcodeVOS = []
	dataForm.productStockVOS = []
	dataForm.imgs = ''

	// 设置监听器状态为 true，允许监听 unitId 的变化
	shouldListenToUnitIdChange = false // 确保在获取商品详情前不触发监听
	if (isUpdate && id) {
		await getProduct(id)
	}else {
		getProductStock()
	}
	shouldListenToUnitIdChange = true // 获取商品详情后允许监听
}

//初始化期初库存
const getProductStock = () => {
	useProductStockApi(-1).then(res => {
		dataForm.productStockVOS = res.data
	})
}
// 获取商品列表
const getProduct = async (id: number) => {
	const res = await useProductApi(id)
	Object.assign(dataForm, res.data)
}

// 表单提交
const submitHandle = () => {
	dataFormRef.value.validate((valid: boolean) => {
		if (!valid) {
			return false
		}

		useProductSubmitApi(dataForm).then(() => {
			ElMessage.success({
				message: '操作成功',
				duration: 500,
				onClose: () => {
					dataFormRef.value.resetFields()
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

<style></style>
