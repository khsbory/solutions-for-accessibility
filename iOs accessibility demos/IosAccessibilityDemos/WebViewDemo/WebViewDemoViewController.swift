//
//  WebViewDemoViewController.swift
//  IosAccessibilityDemos
//
//  Created by Jeonggyu Park on 2020/11/18.
//  Copyright © 2020 Jeonggyu Park. All rights reserved.
//

import UIKit

class WebViewDemoViewController: UIViewController {
   
    @IBAction func launchWebViewDemoWithoutAccessibility(_ sender: Any) {
        print("1")
        showScreenOnOtherStoryboard(storyboardName: "WebViewWithoutA", viewControllerStoryboardId: "webViewWithoutA")
        
    }
    
    @IBAction func launchWebViewDemoWithAccessibility(_ sender: Any) {        
        print("2")
        showScreenOnOtherStoryboard(storyboardName: "WebViewWithA", viewControllerStoryboardId: "webViewWithA")
        
    }
    
    private func showScreenOnOtherStoryboard(storyboardName:String, viewControllerStoryboardId:String) {
        let storyboard = UIStoryboard(name: storyboardName, bundle: nil)
        let viewController = storyboard.instantiateViewController(withIdentifier: viewControllerStoryboardId)
             
        self.present(viewController, animated: true, completion: nil)
    }
    
    
}
