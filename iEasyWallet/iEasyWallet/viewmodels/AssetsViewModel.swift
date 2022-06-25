//
//  AssetsViewModel.swift
//  iEasyWallet
//
//  Created by dejin@crypto.com on 2022/6/25.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class AssetsViewModel: ObservableObject {
    @Published var items = [TokenAsset]()
    private let assetSlug: String
    private let easyApi = KoinHelper.shared.api()
    
    init(slug: String) {
        self.assetSlug = slug
        
        print(easyApi.loadLocalTokenAsset())
        
        easyApi.loadAssets { result, err in
            DispatchQueue.main.async {
                self.items = result!
            }
        }
    }
}
