//
//  FirstPageViewController.swift
//  IosAccessibilityDemos
//
//  Created by Jeonggyu Park on 2021/01/18.
//  Copyright © 2021 Jeonggyu Park. All rights reserved.
//
import UIKit

class FirstPageViewController: UIViewController {
    var index = 0
    override func viewDidLoad() {
        sayScreenNameForAccessibility(screenName: "과일화면")
    }
    
    private func sayScreenNameForAccessibility(screenName: String?) {
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.1, execute: {
                              
                             UIAccessibility.post(notification: .pageScrolled , argument: screenName)
                          })
    }
}
