<template>
	<el-card style="margin-bottom: 10px;">
		<div style="display: flex; justify-content: space-between; margin-bottom: 1rem; margin-right: 20px;">
			<div style="display: flex; align-items: center; gap: 4px; color: var(--el-text-color-secondary); font-size: 14px; margin-left: 20px; padding-top: 10px;">
			  更新至：{{ now }}
			  <el-icon :size="17" style="cursor: pointer;" @click="getHomeData">
			    <RefreshRight />
			  </el-icon>
			</div>
		  <el-segmented 
		    v-model="timeSlot"
		    :options="options"
			
		  />
		</div>
		<el-row :gutter="16">
			<el-col :span="6">
				<div class="statistic-card" style="cursor: pointer">
					<el-statistic :value="saleAmountValue">
						<template #title>
							<div style="display: inline-flex; align-items: center; font-size: 16px;">
								销售金额
								<el-tooltip effect="dark"
									content="统计销售单据的总金额减去退款金额" placement="top">
									<el-icon style="margin-left: 4px" :size="18">
										<Warning />
									</el-icon>
								</el-tooltip>
							</div>
						</template>
					</el-statistic>
					<div class="statistic-footer">
						<div class="footer-item">
							<span>相比昨日</span>
							<span class="green">
								24%
								<el-icon>
									<CaretTop />
								</el-icon>
							</span>
						</div>
					</div>
				</div>
			</el-col>
			<el-col :span="6">
				<div class="statistic-card" style="cursor: pointer">
					<el-statistic :value="grossMarginValue">
						<template #title>
							<div style="display: inline-flex; align-items: center; font-size: 16px;">
								销售毛利
								<!-- <el-tooltip effect="dark"
									content="统计销售单据的毛利" placement="top">
									<el-icon style="margin-left: 4px" :size="18">
										<Warning />
									</el-icon>
								</el-tooltip> -->
							</div>
						</template>
					</el-statistic>
					<div class="statistic-footer">
						<div class="footer-item">
							<span>相比昨日</span>
							<span class="green">
								24%
								<el-icon>
									<CaretTop />
								</el-icon>
							</span>
						</div>
					</div>
				</div>
			</el-col>
			<el-col :span="6">
				<div class="statistic-card" style="cursor: pointer">
					<el-statistic :value="saleCountValue">
						<template #title>
							<div style="display: inline-flex; align-items: center; font-size: 16px;">
								销售笔数
								<!-- <el-tooltip effect="dark"
									content="统计销售单据的笔数" placement="top">
									<el-icon style="margin-left: 4px" :size="18">
										<Warning />
									</el-icon>
								</el-tooltip> -->
							</div>
						</template>
					</el-statistic>
					<div class="statistic-footer">
						<div class="footer-item">
							<span>相比昨日</span>
							<span class="red">
								12%
								<el-icon>
									<CaretBottom />
								</el-icon>
							</span>
						</div>
					</div>
				</div>
			</el-col>
			<el-col :span="6">
				<div class="statistic-card" style="cursor: pointer">
					<el-statistic :value="returnAmountValue" title="New transactions today">
						<template #title>
							<div style="display: inline-flex; align-items: center; font-size: 16px;">
								退货金额
								<el-tooltip effect="dark"
									content="统计销售单据的退货总金额" placement="top">
									<el-icon style="margin-left: 4px" :size="18">
										<Warning />
									</el-icon>
								</el-tooltip>
							</div>
						</template>
					</el-statistic>
					<div class="statistic-footer">
						<div class="footer-item">
							<span>相比昨日</span>
							<span class="green">
								16%
								<el-icon>
									<CaretTop />
								</el-icon>
							</span>
						</div>
					</div>
				</div>
			</el-col>
		</el-row>
		<div style="height: 300px;">
			<div ref="main" style="width: 100%; height: 300px"></div>
		</div>
	</el-card>

	<el-space fill wrap :fill-ratio="28" style="width: 100%">
		<el-card style="width: 100px">
			<template #header>
				<div class="card-header">
					<span>添加微信交流群</span>
				</div>
			</template>
			<div style="height: 220px; display: flex; justify-content: center">
				<el-image src="/images/code.png" />
			</div>
		</el-card>
		<el-card style="width: 600px; height: 255px;">
			<el-tabs v-model="active">
				<el-tab-pane label="项目列表" name="first">
					<el-table :data="tableData" show-overflow-tooltip style="width: 100%">
						<el-table-column prop="name" label="项目名称" width="150" />
						<el-table-column prop="url" label="项目地址" width="310" />
						<el-table-column prop="intro" label="项目介绍" />
					</el-table>
				</el-tab-pane>
			</el-tabs>
		</el-card>
	</el-space>
	
</template>

<script setup lang="ts" name ="DashboardIndex">
	import { useUserStore } from '@/store/modules/user'
	import { useTransition } from '@vueuse/core'
	import { onMounted, ref, onUnmounted, watch, nextTick } from 'vue'
	import * as echarts from 'echarts'
	import type { ECharts, EChartsOption } from 'echarts'
	import type { SegmentedProps } from 'element-plus'
	import { getCurrentDate } from '@/utils/tool'
	import { useHomeDataApi } from '@/api/product/report'
	
	const userStore = useUserStore()
	type TimeRange = 'today' | 'week' | 'month' | 'year'
	
	interface IHomeDataResponse {
	  sale_amount: number
	  gross_margin: number
	  sale_count: number
	  return_amount: number
	  sales_list?: number[]
	}
	
	interface IChartData {
	  chartData: number[]
	  xAxisData: string[]
	}
	
	const timeSlot = ref<TimeRange>('today')
	const options = ref<SegmentedProps['options']>([
	  { label: '本日', value: 'today' }, 
	  { label: '本周', value: 'week' },
	  { label: '本月', value: 'month' }, 
	  { label: '本年', value: 'year' }
	])
	
	// 定义响应式数据
	const saleAmount = ref<number>(0)
	const grossMargin = ref<number>(0)
	const saleCount = ref<number>(0)
	const returnAmount = ref<number>(0)
	
	// 使用过渡效果
	const saleAmountValue = useTransition(saleAmount, { duration: 600 })
	const grossMarginValue = useTransition(grossMargin, { duration: 600 })
	const saleCountValue = useTransition(saleCount, { duration: 600 })
	const returnAmountValue = useTransition(returnAmount, { duration: 600 })
	
	const active = ref<string>('first')
	const now = ref<string>('')
	const main = ref<HTMLElement | null>(null)
	const chartInstance = ref<ECharts | null>(null)
	const chartData = ref<number[]>([])
	const xAxisData = ref<string[]>([])
	
	// 生成图表数据的方法
	const generateChartData = (timeRange: TimeRange, apiData: IHomeDataResponse): IChartData => {
	  let chartData: number[] = []
	  let xAxisData: string[] = []
	  
	  switch(timeRange) {
	    case 'today':
	      xAxisData = Array.from({length: 24}, (_, i) => `${i}:00`)
	      chartData = apiData.sales_list || Array(24).fill(0)
	      break
	      
	    case 'week':
	      xAxisData = ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
	      chartData = apiData.sales_list || Array(7).fill(0)
	      break
	      
	    case 'month':
	      const daysInMonth = new Date().getDate()
	      xAxisData = Array.from({length: daysInMonth}, (_, i) => `${i+1}日`)
	      chartData = apiData.sales_list || Array(daysInMonth).fill(0)
	      break
	      
	    case 'year':
	      xAxisData = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
	      chartData = apiData.sales_list || Array(12).fill(0)
	      break
	  }
	  
	  return { chartData, xAxisData }
	}
	
	// 获取数据并更新图表
	const getHomeData = async (): Promise<void> => {
	  try {
	    const res = await useHomeDataApi(timeSlot.value)
	    const data = res.data as IHomeDataResponse
	    
	    // 更新统计卡片数据
	    saleAmount.value = data.sale_amount
	    saleCount.value = data.sale_count
	    returnAmount.value = data.return_amount
	    grossMargin.value = data.gross_margin
	    getNow()
	    
	    // 生成图表数据
	    const { chartData: newChartData, xAxisData: newXAxisData } = generateChartData(timeSlot.value, data)
	    chartData.value = newChartData
	    xAxisData.value = newXAxisData
	    
	    // 更新图表
	    updateChart()
	  } catch (error) {
	    console.error('获取首页数据失败:', error)
	    // 可以在这里添加错误处理逻辑，比如显示提示消息
	  }
	}
	
	// 更新图表配置
	const updateChart = (): void => {
	  if (!chartInstance.value) return
	  
	  const option: EChartsOption = {
	    xAxis: {
	      type: 'category',
	      data: xAxisData.value
	    },
	    yAxis: {
	      type: 'value'
	    },
	    series: [{
	      data: chartData.value,
	      type: 'line',
	      lineStyle: {
	        color: getComputedStyle(document.documentElement)
	          .getPropertyValue('--el-color-primary').trim(),
	        width: 3
	      },
	      label: {
	        show: true,
	        position: 'top',
	        color: '#333',
	        fontSize: 12
	      }
	    }]
	  }
	  
	  chartInstance.value.setOption(option, true) // 第二个参数true表示不合并配置
	}
	
	// 初始化图表
	const initChart = (): void => {
	  if (!main.value) return
	  
	  // 如果已有实例，先销毁
	    if (chartInstance.value) {
	      chartInstance.value.dispose()
	      chartInstance.value = null
	    }
		
	  chartInstance.value = echarts.init(main.value)
	  updateChart()
	  
	  // 添加窗口resize监听
	  window.addEventListener('resize', handleResize)
	}
	
	// 处理窗口resize
	const handleResize = (): void => {
	  chartInstance.value?.resize()
	}
	
	// 获取当前时间
	const getNow = (): void => {
	  now.value = getCurrentDate()
	}
	
	// 监听时间范围变化
	watch(timeSlot, getHomeData)
	
	// 生命周期钩子
	onMounted(() => {
		initChart()
		getHomeData()
	})
	
	onUnmounted(() => {
	  // 清理资源
	  window.removeEventListener('resize', handleResize)
	  if (chartInstance.value) {
	      chartInstance.value.dispose() // 正确销毁图表
	      chartInstance.value = null
	    }
	})
	
	const tableData = [
		{
			name: 'gitee地址',
			url: 'https://gitee.com/crazy-code/grape-erp',
			intro: '企业级ERP，使用门槛极低。'
		},
		{
			name: 'gitcode地址',
			url: 'https://gitcode.com/crazy_laoma/grape-erp',
			intro: '企业级ERP，使用门槛极低。'
		}
	]
</script>

<style scoped>
	:global(h2#card-usage ~ .example .example-showcase) {
		background-color: var(--el-fill-color) !important;
	}

	.el-statistic {
		--el-statistic-content-font-size: 28px;
	}

	.statistic-card {
		height: 100%;
		padding: 20px;
		border-radius: 4px;
		background-color: var(--el-bg-color-overlay);
		display: flex;
		  flex-direction: column;
		  align-items: center; /* 水平居中 */
		  justify-content: center; /* 垂直居中 */
		  text-align: center; /* 文本居中 */
	}

	.statistic-footer {
		display: flex;
		justify-content: space-between;
		align-items: center;
		flex-wrap: wrap;
		font-size: 14px;
		color: var(--el-text-color-regular);
		margin-top: 16px;
	}

	.statistic-footer .footer-item {
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.statistic-footer .footer-item span:last-child {
		display: inline-flex;
		align-items: center;
		margin-left: 4px;
		
	}

	.green {
		color: var(--el-color-success);
	}

	.red {
		color: var(--el-color-error);
	}
</style>