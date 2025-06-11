<template>
	<el-card>
		<el-tabs v-model="activeName">
			<el-tab-pane v-auth="'product:grContactunitsCategory:list'" label="供应商 / 客户" name="contactunits">
				<ContactunitsCategory />
			</el-tab-pane>
			<el-tab-pane v-auth="'product:grProductCategory:list'" label="商品" name="product">
				<ProductCategory />
			</el-tab-pane>
			<el-tab-pane v-auth="'product:grBrandCategory:list'" label="品牌" name="brand">
				<BrandCategory />
			</el-tab-pane>
		</el-tabs>
	</el-card>
</template>

<script setup lang="ts" name="ProductCategoryIndex">
import { ref } from 'vue'
import ProductCategory from './product/index.vue'
import ContactunitsCategory from './contactunits/index.vue'
import BrandCategory from './brand/index.vue'

const activeName = ref('contactunits')
</script>
