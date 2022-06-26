import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        let userDefaults = UserDefaults(suiteName: "EASYWALLET_SETTINGS")
        HelperKt.doInitKoin(userDefaults: userDefaults!)
    }
    
	var body: some Scene {
		WindowGroup {
			MainView()
		}
	}
}
