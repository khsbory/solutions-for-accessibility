//
//  AccessibilityFocusDemoWithoutAViewController.swift
//  IosAccessibilityDemos
//
//  Created by Jeonggyu Park on 2020/12/04.
//  Copyright © 2020 Jeonggyu Park. All rights reserved.
//

import UIKit

class AccessibilityFocusDemoWithoutAViewController: UIViewController {
    
    @IBOutlet weak var label1: UILabel!
    
    @IBOutlet weak var label2: UILabel!
    
    @IBOutlet weak var label3: UILabel!
    
    
    @IBOutlet weak var button1: UIView!
    
    @IBOutlet weak var button2: UIView!
    
    @IBOutlet weak var button3: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
      
        makeLayout()
        addTapListenerToButtons()
    }
    
    private func makeLayout() {
        // Create Attachment
        let imageAttachment = NSTextAttachment()
        imageAttachment.image = UIImage(named:"bank")
        // Set bound to reposition       
        imageAttachment.bounds = CGRect(x: 0, y: 0, width: 20, height: 20)
        // Create string with attachment
        let attachmentString = NSAttributedString(attachment: imageAttachment)
        // Initialize mutable string
        let completeText = NSMutableAttributedString(string: "")
        // Add image to mutable string
        completeText.append(attachmentString)
        // Add your text to mutable string
        let textAfterIcon = NSAttributedString(string: "Using attachment.bounds!")
        completeText.append(textAfterIcon)
        self.label1.textAlignment = .center
        self.label1.attributedText = completeText
        
        self.label2.textAlignment = .center
        self.label2.attributedText = completeText
        
        
        self.label3.textAlignment = .center
        self.label3.attributedText = completeText
        
        
    }
    
    private func addTapListenerToButtons() {
        
        
        
        
        
        
        let button1TapGesture = UITapGestureRecognizer(target: self, action: #selector(self.onButton1Tapped(_:)))
        button1TapGesture.numberOfTapsRequired = 1
        button1TapGesture.numberOfTouchesRequired = 1
        button1.addGestureRecognizer(button1TapGesture)
        button1.isUserInteractionEnabled = true
        
        let button2TapGesture = UITapGestureRecognizer(target: self, action: #selector(self.onButton2Tapped(_:)))
        button2TapGesture.numberOfTapsRequired = 1
        button2TapGesture.numberOfTouchesRequired = 1
        button2.addGestureRecognizer(button2TapGesture)
        button2.isUserInteractionEnabled = true

        let button3TapGesture = UITapGestureRecognizer(target: self, action: #selector(self.onButton3Tapped(_:)))
        button3TapGesture.numberOfTapsRequired = 1
        button3TapGesture.numberOfTouchesRequired = 1
        button3.addGestureRecognizer(button3TapGesture)
        button3.isUserInteractionEnabled = true
        
    }
    
    @objc func onButton1Tapped(_ sender: UITapGestureRecognizer) {
        print("onButton1Tapped")
    }
    
    @objc func onButton2Tapped(_ sender: UITapGestureRecognizer) {
        print("onButton2Tapped")
    }
    
    
    @objc func onButton3Tapped(_ sender: UITapGestureRecognizer) {
        print("onButton3Tapped")
    }
}
