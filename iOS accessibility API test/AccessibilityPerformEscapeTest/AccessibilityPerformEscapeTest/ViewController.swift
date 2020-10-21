//
//  ViewController.swift
//  AccessibilityPerformEscapeTest
//
//  Created by Jeonggyu Park on 21/10/2020.
//  Copyright © 2020 Jeonggyu Park. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet var button: UIButton!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.buttonTapped(_:)))
        tapGesture.numberOfTapsRequired = 1
        tapGesture.numberOfTouchesRequired = 1
        button.addGestureRecognizer(tapGesture)
        button.isUserInteractionEnabled = true
        
    }
    
    @objc func buttonTapped(_ sender: UITapGestureRecognizer) {
       let viewController  = self.storyboard?.instantiateViewController(withIdentifier: "next")
         self.present(viewController!, animated: true, completion: nil)
    }

//   override func accessibilityPerformEscape() -> Bool {
//   return true
//    }
}

