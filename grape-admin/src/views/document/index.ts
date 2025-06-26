export interface DocumentDetail {
	id: number | null //条码id
	productId: number | null
	storeId: number | null
	specs: string
	productName: string
	productNumber: string
	barcode: string
	storeName?: string
	quantity: number | null
	unitId: string
	unitName?: string
	unitPrice?: number | null
	expectPurchasePrice?: number | null
	amount?: number | null
	taxRate?: number | null
	taxAmount?: number | null
	finalAmount?: number | null
	remark?: string | null
	projectId?: number | null
	inStoreId?: number | null
	stockCount?: number | null
	img?: string | null
}

export interface AccountDetail {
	accountName: string
	amount: number
}

import { useContactunitsApi } from '@/api/product/contactunits'

interface AdvanceData {
  advanceIn: number
  advanceOut: number
}

export async function getContactunitsAdvanceIn(id: number) : Promise<number> {
  const { advanceIn } = await getContactunitsAdvance(id)
  return advanceIn
}

export async function getContactunitsAdvanceOut(id: number) : Promise<number> {
  const { advanceOut } = await getContactunitsAdvance(id)
  return advanceOut
}

async function  getContactunitsAdvance(id: number): Promise<AdvanceData> {
  const res = await useContactunitsApi(id)
  return {
      advanceIn: res.data.advanceIn,
      advanceOut: res.data.advanceOut
    }
}

interface RouterPathMap {
  [key: string]: string
}

const routerPaths: RouterPathMap = {
	'02': '/document/order/index',
	'03': '/document/orderReturn/index',
	'13': '/document/sale/index',
	'14': '/document/saleReturn/index',
	'21': '/document/allot/index',
	'22': '/document/otherIn/index',
	'23': '/document/otherOut/index',
	'32': '/document/settlement/payment/index',
	'34': '/document/settlement/receivePayment/index',
	'36': '/document/settlement/expend/index',
	'37': '/document/settlement/income/index',
	'38': '/document/settlement/rePayment/index',
	'39': '/document/settlement/reReceivePayment/index'
}

export const getRouterPath = (documentType: string): string => {
  const path = routerPaths[documentType]
  if (!path) {
    throw new Error(`Invalid documentType: ${documentType}`)
  }
  return path
}