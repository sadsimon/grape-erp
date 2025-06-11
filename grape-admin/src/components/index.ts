import type { App, Plugin } from 'vue'
import MaDictColumn from '@/components/ma-dict/ma-dict-column/index.vue'
import MaDictLabel from '@/components/ma-dict/ma-dict-label/index.vue'
import MaDictRadio from '@/components/ma-dict/ma-dict-radio/index.vue'
import MaDictTreeSelect from '@/components/ma-dict/ma-dict-tree-select/index.vue'
import MaDictSelect from '@/components/ma-dict/ma-dict-select/index.vue'
import MaDictCheckbox from '@/components/ma-dict/ma-dict-checkbox/index.vue'
import MaDataColumn from '@/components/ma-data/ma-data-column/index.vue'
import MaDataLabel from '@/components/ma-data/ma-data-label/index.vue'
import MaDataSelect from '@/components/ma-data/ma-data-select/index.vue'
import MaDataTreeSelect from '@/components/ma-data/ma-data-tree-select/index.vue'
import MaDataTreeLeft from '@/components/ma-data/ma-data-tree-left/index.vue'
import MaDataTable from '@/components/ma-data/ma-data-table/index.vue'
import MaImageColumn from '@/components/ma-image-column/index.vue'

import MaIcon from '@/components/ma-icon/index.vue'
import MaEditor from '@/components/ma-editor/index.vue'
import MaMarkdown from '@/components/ma-markdown/index.vue'
import MaAddress from '@/components/ma-address/index.vue'
import GrAddressInput from '@/components/gr-input/gr-address-input/index.vue'
import MaUserInput from '@/components/ma-user/ma-user-input/index.vue'
import MaUserDialog from '@/components/ma-user/ma-user-dialog/index.vue'
import MaOrgSelect from '@/components/ma-org/ma-org-select/index.vue'
import MaOrgInput from '@/components/ma-org/ma-org-input/index.vue'
import MaRoleInput from '@/components/ma-role-input/index.vue'
import MaPostInput from '@/components/ma-post-input/index.vue'
import MaUploadImage from '@/components/ma-upload/ma-upload-image/index.vue'
import MaUploadImages from '@/components/ma-upload/ma-upload-images/index.vue'
import MaUploadFile from '@/components/ma-upload/ma-upload-file/index.vue'
import MaUploadExcel from '@/components/ma-upload/ma-upload-excel/index.vue'

import GrProductInput from '@/components/gr-product/gr-product-input/index.vue'
import GrStoreInput from '@/components/gr-input/gr-store-input/index.vue'
import GrStoreMultipleInput from '@/components/gr-input/gr-store-input/multiple/index.vue'
import StoreAddOrUpdate from '@/components/gr-input/gr-store-input/store-add-or-update.vue'
import GrContactunitsInput from '@/components/gr-input/gr-contactunits-input/index.vue'
import GrBrandInput from '@/components/gr-input/gr-brand-input/index.vue'
import BrandAddOrUpdate from '@/components/gr-input/gr-brand-input/brand-add-or-update.vue'
import GrSingleUnitInput from '@/components/gr-input/gr-unit-input/single/index.vue'
import GrSingleUnitAddOrUpdate from '@/components/gr-input/gr-unit-input/single/add-or-update/index.vue'
import GrMultipleUnitInput from '@/components/gr-input/gr-unit-input/multiple/index.vue'
import GrMultipleUnitAddOrUpdate from '@/components/gr-input/gr-unit-input/multiple/add-or-update/index.vue'
import GrSettlementAccountInput from '@/components/gr-input/gr-settlementAccount-input/index.vue'
import GrDialog from '@/components/gr-dialog/index.vue'
import MaDataTreeLeft2 from '@/components/ma-data/ma-data-tree-left2/index.vue'
import GrNumberInput from '@/components/gr-input/gr-input-number/index.vue'
import GrDataSwitch from '@/components/ma-data/gr-data-switch/index.vue'
import GrDataTableEdit from '@/components/ma-data/gr-data-table-edit/index.vue'
import GrSelectableTable from '@/components/ma-data/gr-data-table-edit2/index.vue'
import GrField from '@/components/gr-field/index.vue'
import GrStamp from '@/components/gr-document/gr-document-stamp/index.vue'
import GrDocumentFoot from '@/components/gr-document/gr-document-foot/index.vue'
import GrIncomeExpendInput from '@/components/gr-input/gr-incomeExpend-input/index.vue'
import IncomeExpendAddOrUpdate from '@/components/gr-input/gr-incomeExpend-input/IncomeExpend-add-or-update.vue'

const components = [
	MaEditor,
	MaMarkdown,
	MaAddress,
	MaIcon,
	MaDictLabel,
	MaDictColumn,
	MaDictRadio,
	MaDictSelect,
	MaDictCheckbox,
	MaDictTreeSelect,
	MaDataColumn,
	MaDataLabel,
	MaDataSelect,
	MaDataTreeSelect,
	MaDataTreeLeft,
	MaDataTable,
	MaImageColumn,
	MaUserInput,
	MaUserDialog,
	MaOrgSelect,
	MaOrgInput,
	MaRoleInput,
	MaPostInput,
	MaUploadImage,
	MaUploadImages,
	MaUploadFile,
	MaUploadExcel,
	GrDataTableEdit,
	GrProductInput,
	GrAddressInput,
	GrStoreInput,
	StoreAddOrUpdate,
	GrContactunitsInput,
	GrBrandInput,
	BrandAddOrUpdate,
	GrSingleUnitInput,
	GrSingleUnitAddOrUpdate,
	GrMultipleUnitInput,
	GrMultipleUnitAddOrUpdate,
	GrSettlementAccountInput,
	GrDialog,
	MaDataTreeLeft2,
	GrNumberInput,
	GrDataSwitch,
	GrSelectableTable,
	GrField,
	GrStamp,
	GrDocumentFoot,
	GrStoreMultipleInput,
	GrIncomeExpendInput,
	IncomeExpendAddOrUpdate
]

const MaComponent: Plugin = {
	install(Vue: App) {
		components.forEach((component: any) => {
			Vue.component(component.name, component)
		})
	}
}

export default MaComponent
