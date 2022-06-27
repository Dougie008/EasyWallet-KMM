//
//  AssetsView.swift
//  iEasyWallet
//
//  Created by dejin@crypto.com on 2022/6/25.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import Kingfisher

struct AssetsView: View {
    @ObservedObject var viewModel = AssetsViewModel(slug: "Ethereum")
    
    var body: some View {
        VStack {
            TopbarView()
            AssetListContent(
                loading: viewModel.loading,
                assets: viewModel.items
            ).onAppear() {
                viewModel.activate()
            }.onDisappear() {
                viewModel.deactivate()
            }
        }
    }
}

struct AssetListContent: View {
    var loading: Bool
    var assets: [TokenAsset]?
    
    var body: some View {
        ZStack {
            if let assets = assets {
                List (assets, id: \.self) { item in
                    NavigationLink{
                        Text(item.icon)
                    } label: {
                        HStack {
                            KFImage.url(URL(string: item.icon))
                                .fade(duration: 0.25)
                                .resizable()
                                .frame(width: 40.0, height: 40.0)
                            Text(item.symbol)
                        }.id(item.slug)
                        Spacer()
                        Text("\(item.balance)")
                    }
                }
            }
            if loading {
                Text("Loading...")
            }
        }
    }
}

struct AssetsView_Previews: PreviewProvider {
    static var previews: some View {
        AssetsView()
    }
}
