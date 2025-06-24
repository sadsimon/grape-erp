import type { App } from 'vue'
import { auth } from './auth'

export const directive = (app: App) => {
	auth(app)
}
