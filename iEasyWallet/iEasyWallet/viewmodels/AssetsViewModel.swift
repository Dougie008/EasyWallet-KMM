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
    
    private let hdWallet = HDWallet(mnemonic: "credit expect life fade cover suit response wash pear what skull force", passphrase: "")
    
    private var cancellables = [AnyCancellable]()
    
    init(slug: String) {
        self.assetSlug = slug
    }
    
    func activate() {
        let viewModel = KoinHelper.shared.getAssetsViewModel()
        viewModel.inject { assets in
            assets.map { asset in
                switch asset.chain {
                case NetworkChain.ethereum:
                    return asset.doCopy(slug: asset.slug, symbol: asset.symbol, icon: asset.icon, chain: asset.chain, decimal: asset.decimal, contractAddress: asset.contractAddress, tag: asset.tag, address: self.hdWallet?.getAddressForCoin(coin: .ethereum), balance: asset.balance)
                case NetworkChain.solana:
                    return asset.doCopy(slug: asset.slug, symbol: asset.symbol, icon: asset.icon, chain: asset.chain, decimal: asset.decimal, contractAddress: asset.contractAddress, tag: asset.tag, address: self.hdWallet?.getAddressForCoin(coin: .solana), balance: asset.balance)
                default:
                    return asset.doCopy(slug: asset.slug, symbol: asset.symbol, icon: asset.icon, chain: asset.chain, decimal: asset.decimal, contractAddress: asset.contractAddress, tag: asset.tag, address: self.hdWallet?.getAddressForCoin(coin: .ethereum), balance: asset.balance)
                }
            }
        }
        doPublish(viewModel.assetsState) { [weak self] assetsState in
            self?.loading = assetsState.isLoading
            self?.items = assetsState.tokenAssets
            assetsState.tokenAssets.forEach { item in
                print("balance: \(item.balance)")
            }
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
