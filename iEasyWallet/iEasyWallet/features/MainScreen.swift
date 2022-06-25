//
//  MainScreen.swift
//  iEasyWallet
//
//  Created by dejin@crypto.com on 2022/6/25.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct MainView: View {
    let gradient = LinearGradient(
        colors: [.blue.opacity(0.3), .green.opacity(0.5)],
        startPoint: .topLeading,
        endPoint: .bottomTrailing)
    var body: some View {
        NavigationView {
            TabView {
                AssetsView().tabItem {
                    Label("Assets", systemImage: "house.fill")
                }.tag("ASSETS").navigationBarHidden(true)
                DAppsView().tabItem {
                    Label("DApps", systemImage: "magnifyingglass")
                }.tag("DAPPS").navigationBarHidden(true)
            }
        }
    }
}
