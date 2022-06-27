//
//  AssetsViewModel.swift
//  iEasyWallet
//
//  Created by dejin@crypto.com on 2022/6/25.
//  Copyright © 2022 orgName. All rights reserved.
//

import Combine
import SwiftUI
import shared
import WalletCore

class AssetsViewModel: ObservableObject {
    
    private let assetSlug: String
    private var viewModel: AssetsCallbackViewModel?
    
    @Published var loading = false
    @Published var items = [TokenAsset]()
    
    private var cancellables = [AnyCancellable]()
    
    init(slug: String) {
        self.assetSlug = slug
    }
    
    func activate() {
        let viewModel = KoinHelper.shared.getAssetsViewModel()
        doPublish(viewModel.assetsState) { [weak self] assetsState in
            self?.loading = assetsState.isLoading
            self?.items = assetsState.tokenAssets
            
            print(assetsState.localContent)
        }.store(in: &cancellables)
        
        self.viewModel = viewModel
    }
    
    func deactivate() {
        cancellables.forEach { $0.cancel() }
        cancellables.removeAll()

        viewModel?.clear()
        viewModel = nil
    }
}
