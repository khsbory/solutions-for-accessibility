//
//  FirstPageViewController.swift
//  IosAccessibilityDemos
//
//  Created by Jeonggyu Park on 2021/01/18.
//  Copyright © 2021 Jeonggyu Park. All rights reserved.
//
import UIKit

class FilterDemoViewController: UIViewController {
   
    @IBOutlet weak var sideMenu: UIView!
    @IBOutlet weak var backButton: UIImageView!
 
    @IBAction func onFilterButtonClicked(_ sender: Any) {
        sideMenu.isHidden = false
        
    }
    override func viewDidLoad() {
        initBackButton()
    }
    
    private func initBackButton() {
        backButton.accessibilityTraits = .button
        backButton.isAccessibilityElement = true
        backButton.accessibilityLabel = "뒤로가기"
        backButton.isUserInteractionEnabled = true
        backButton.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onBakButtonClicked)))
     }
    
    
    @objc func onBakButtonClicked(){
        self.dismiss(animated: true, completion: nil)
    }
}
