//
//  TopbarView.swift
//  iEasyWallet
//
//  Created by dejin@crypto.com on 2022/6/25.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct TopbarView: View {
    
    @State var active: Bool = true
    
    var body: some View {
        HStack {
            NavigationLink {
                SettingsView()
            } label: {
                Image("icon_assets")
            }
            
            VStack {
                Text("Wallet Name").font(.system(size: 14))
                Text("View Settings").font(.system(size: 12))
            }
            Spacer()
            NavigationLink(destination: Text("hello")) {
                Image("icon_dapps")
            }
        
        }.padding(EdgeInsets(top: 0.0, leading: 16.0, bottom: 0.0, trailing: 16.0))
    }
}

struct TopbarView_Previews: PreviewProvider {
    static var previews: some View {
        TopbarView()
    }
}
