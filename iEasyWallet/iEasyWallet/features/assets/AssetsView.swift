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
    let helper = KoinHelper()
    
    @State var assets = [TokenAsset]()
    
    func load() {
        helper.loadAssets { result, error in
            self.assets = result!
        }
    }
    
    var body: some View {
        VStack {
            TopbarView()
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
                }
            }
        }.onAppear() {
            load()
        }
    }
}

struct AssetsView_Previews: PreviewProvider {
    static var previews: some View {
        AssetsView()
    }
}
