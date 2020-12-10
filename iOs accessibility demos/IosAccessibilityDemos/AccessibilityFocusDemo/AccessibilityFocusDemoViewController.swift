//
//  WebViewDemoViewController.swift
//  IosAccessibilityDemos
//
//  Created by Jeonggyu Park on 2020/11/18.
//  Copyright © 2020 Jeonggyu Park. All rights reserved.
//

import UIKit

class AccessibilityFocusDemoViewController: UIViewController {
   
    @IBAction func launchAccessibilityFocusDemoWithoutAccessibility(_ sender: Any) {
        print("1")
        showScreenOnOtherStoryboard(storyboardName: "AccessibilityFocusDemoWithoutA", viewControllerStoryboardId: "accessibilityFocusDemoWithoutA")
        
    }
    
    @IBAction func launchAccessibilityFocusDemoWithAccessibility(_ sender: Any) {
        print("2")
        showScreenOnOtherStoryboard(storyboardName: "AccessibilityFocusDemoWithA", viewControllerStoryboardId: "accessibilityFocusDemoWithA")
        
    }
    
    private func showScreenOnOtherStoryboard(storyboardName:String, viewControllerStoryboardId:String) {
        let storyboard = UIStoryboard(name: storyboardName, bundle: nil)
        let viewController = storyboard.instantiateViewController(withIdentifier: viewControllerStoryboardId)
             
        self.present(viewController, animated: true, completion: nil)
    }
    
    
}
