//
//  VerticalScrollDemo2MainViewController.swift
//  IosAccessibilityDemos
//
//  Created by Jeonggyu Park on 2021/01/18.
//  Copyright © 2021 Jeonggyu Park. All rights reserved.
//

import UIKit

class FilterDemoMainViewController: UIViewController {
    
    @IBOutlet weak var backButton: UIImageView!
 
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initBackButton()
          
    }
   
    @IBAction func launchViewControllerWithoutAccessibility(_ sender: Any) {
         showScreenOnOtherStoryboard(storyboardName: "FilterDemo", viewControllerStoryboardId: "filterDemo")
        
    }
    
    @IBAction func launchViewControllerWithAccessibility(_ sender: Any) {
        showScreenOnOtherStoryboard(storyboardName: "FilterDemoWA", viewControllerStoryboardId: "filterDemoWA")
        
    }
    
    private func showScreenOnOtherStoryboard(storyboardName:String, viewControllerStoryboardId:String) {
        let storyboard = UIStoryboard(name: storyboardName, bundle: nil)
        let viewController = storyboard.instantiateViewController(withIdentifier: viewControllerStoryboardId)
             
        self.present(viewController, animated: true, completion: nil)
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

