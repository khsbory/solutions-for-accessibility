//
//  FirstPageViewController.swift
//  IosAccessibilityDemos
//
//  Created by Jeonggyu Park on 2021/01/18.
//  Copyright © 2021 Jeonggyu Park. All rights reserved.
//
import UIKit


class FilterDemoViewController: UIViewController {
    
    
    @IBOutlet weak var number1HeightConstraint: NSLayoutConstraint!
    
    @IBOutlet weak var number2HeightConstraint: NSLayoutConstraint!
    
    @IBOutlet weak var number3HeightConstraint: NSLayoutConstraint!
    
    
    @IBOutlet weak var alphabet1HeightConstraint: NSLayoutConstraint!
    
    @IBOutlet weak var alphabet2HeightConstraint: NSLayoutConstraint!
    
    
    @IBOutlet weak var alphabet3HeightConstraint: NSLayoutConstraint!
    
    @IBOutlet weak var number1Label: UILabel!
    
    @IBOutlet weak var number2Label: UILabel!
    
    @IBOutlet weak var number3Label: UILabel!
    @IBOutlet weak var number1Check: UIImageView!
    
    @IBOutlet weak var number2Check: UIImageView!
    
    
    @IBOutlet weak var number3Check: UIImageView!
    
    @IBOutlet weak var alphabet1Radio: UIImageView!
    
    @IBOutlet weak var alphabet2Radio: UIImageView!
    
    
    @IBOutlet weak var alphabet3Radio: UIImageView!
    
    
    @IBOutlet weak var numberSection: UIView!
    
    @IBOutlet weak var alphabetSection: UIView!
    
    
    @IBOutlet weak var alphabet1Label: UILabel!
    
    @IBOutlet weak var alphabet2Label: UILabel!
    
    @IBOutlet weak var alphabet3Label: UILabel!
    
    
    @IBOutlet weak var filterButton: UIButton!
    
    @IBOutlet weak var filterResult: UILabel!
    
    
    
    
    @IBOutlet weak var mainScreen: UIView!
    
    @IBOutlet weak var topBar: UIView!
    @IBOutlet weak var sideMenu: UIView!
    @IBOutlet weak var backButton: UIImageView!
    
    private let UNSELECTED: Int = 0
    private let SELECTED: Int = 1
    
    @IBAction func onFilterButtonClicked(_ sender: Any) {
        sideMenu.isHidden = false
        
        if (Constants.isAccessibilityApplied) {
            mainScreen.accessibilityElementsHidden = true
            topBar.accessibilityElementsHidden = true
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 1, execute: {
                UIAccessibility.post(notification: .screenChanged , argument: "필터 열림")
            })
            
            
            
        }
        
        
    }
    override func viewDidLoad() {
        initAlphabetSection()
        initNumberSection()
        initBackButton()
        initNumberCheck()
        initAlphabetRadio()
        initFilterResult()
    }
    
    private func initFilterResult() {
        filterResult.isAccessibilityElement = false
    }
    
    
    @IBAction func closeSideMenu(_ sender: Any) {
        sideMenu.isHidden = true
        
        let selectedFilterCount = number1Check.tag + number2Check.tag + number3Check.tag + alphabet1Radio.tag + alphabet2Radio.tag + alphabet3Radio.tag
        filterResult.text = String(selectedFilterCount)
        filterButton.accessibilityLabel = "필터 선택된 필터 " + String(selectedFilterCount) + "개"
        
        
        if (Constants.isAccessibilityApplied) {
            mainScreen.accessibilityElementsHidden = false
            topBar.accessibilityElementsHidden = false
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 1, execute: {
                UIAccessibility.post(notification: .screenChanged , argument: "필터 닫힘")
            })
            
            
            
        }
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
    
    
    /*
     숫자, 알파벳 섹션 펼치기 접기
     */
    
    private func initNumberSection() {
        numberSection.isUserInteractionEnabled = true
        numberSection.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onNumberSectionClicked)))
    }
    
    
    @objc func onNumberSectionClicked(){
        if (numberSection.tag == UNSELECTED) {
            openNumberSection()
        } else if (numberSection.tag == SELECTED ) {
            closeNumberSection()
        }
    }
    
    private func initAlphabetSection() {
        alphabetSection.isUserInteractionEnabled = true
        alphabetSection.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onAlphabetSectionClicked)))
    }
    
    
    
    @objc func onAlphabetSectionClicked(){
        
        if (alphabetSection.tag == UNSELECTED) {
            openAlphabetSection()
        } else if (alphabetSection.tag == SELECTED ) {
            closeAlphabetSection()
        }
    }
    
    private func openAlphabetSection() {
        if (Constants.isAccessibilityApplied) {
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 1, execute: {
                UIAccessibility.post(notification: .screenChanged , argument: "알파벳 필터 펼치기")
            })
            
            
            
        }
        
        alphabet1Label.isHidden = false
        alphabet2Label.isHidden = false
        alphabet3Label.isHidden = false
        
        alphabet1Radio.isHidden = false
        alphabet2Radio.isHidden = false
        alphabet3Radio.isHidden = false
        
        alphabet1HeightConstraint.constant = 60
        alphabet2HeightConstraint.constant = 60
        alphabet3HeightConstraint.constant = 60
        
        alphabetSection.tag = SELECTED
    }
    
    private func closeAlphabetSection() {
        if (Constants.isAccessibilityApplied) {
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 1, execute: {
                UIAccessibility.post(notification: .screenChanged , argument: "알파벳 필터 접기")
            })
            
            
            
        }
        
        alphabet1Label.isHidden = true
        alphabet2Label.isHidden = true
        alphabet3Label.isHidden = true
        
        alphabet1Radio.isHidden = true
        alphabet2Radio.isHidden = true
        alphabet3Radio.isHidden = true
        
        
        alphabet1HeightConstraint.constant = 0
        alphabet2HeightConstraint.constant = 0
        alphabet3HeightConstraint.constant = 0
        
        alphabetSection.tag = UNSELECTED
        
    }
    
    private func openNumberSection() {
        
        if (Constants.isAccessibilityApplied) {
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 1, execute: {
                UIAccessibility.post(notification: .screenChanged , argument: "숫자 필터 펼치기")
            })
            
            
            
        }
        
        
        number1Label.isHidden = true
        number2Label.isHidden = true
        number3Label.isHidden = true
        
        number1Check.isHidden = true
        number2Check.isHidden = true
        number3Check.isHidden = true
        
        
        number1HeightConstraint.constant = 60
        number2HeightConstraint.constant = 60
        number3HeightConstraint.constant = 60
        
        numberSection.tag = SELECTED
        
    }
    
    private func closeNumberSection() {
        if (Constants.isAccessibilityApplied) {
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 1, execute: {
                UIAccessibility.post(notification: .screenChanged , argument: "숫자 필터 접기")
            })
            
            
            
        }
        number1Label.isHidden = false
        number2Label.isHidden = false
        number3Label.isHidden = false
        
        number1Check.isHidden = false
        number2Check.isHidden = false
        number3Check.isHidden = false
        
        
        
        number1HeightConstraint.constant = 60
        number2HeightConstraint.constant = 60
        number3HeightConstraint.constant = 60
        
        numberSection.tag = UNSELECTED
        
    }
    
    
    
    
    /*
     숫자 check 버튼
     */
    
    
    private func initNumberCheck() {
        
        number1Label.accessibilityTraits = .button
        number1Label.isUserInteractionEnabled = true
        number1Label.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onNumber1CheckClicked)))
        number1Check.isUserInteractionEnabled = true
        number1Check.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onNumber1CheckClicked)))
        
        number2Label.accessibilityTraits = .button
        number2Label.isUserInteractionEnabled = true
        number2Label.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onNumber2CheckClicked)))
        number2Check.isUserInteractionEnabled = true
        number2Check.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onNumber2CheckClicked)))
        
        number3Label.accessibilityTraits = .button
        number3Label.isUserInteractionEnabled = true
        number3Label.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onNumber3CheckClicked)))
        number3Check.isUserInteractionEnabled = true
        number3Check.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onNumber3CheckClicked)))
        
    }
    
    @objc func onNumber1CheckClicked(){
        
        if (number1Check.tag == SELECTED) {
            
            number1Check.image = UIImage(named: "uncheck")
            number1Check.tag = UNSELECTED
            
            if (Constants.isAccessibilityApplied) {
                number1Label.accessibilityTraits = .button
            }
            
        } else if (number1Check.tag == UNSELECTED ){
            if (Constants.isAccessibilityApplied) {
                number1Label.accessibilityTraits = .selected
            }
            
            number1Check.image = UIImage(named: "check")
            number1Check.tag = SELECTED
        }
        
    }
    
    @objc func onNumber2CheckClicked(){
        if (number2Check.tag == SELECTED) {
            if (Constants.isAccessibilityApplied) {
                number2Label.accessibilityTraits = .button
            }
            
            number2Check.image = UIImage(named: "uncheck")
            number2Check.tag = UNSELECTED
            
        } else if (number2Check.tag == UNSELECTED ){
            if (Constants.isAccessibilityApplied) {
                
                number2Label.accessibilityTraits = .selected
            }
            
            number2Check.image = UIImage(named: "check")
            number2Check.tag = SELECTED
            
            
        }
        
    }
    
    
    @objc func onNumber3CheckClicked(){
        if (number3Check.tag == SELECTED) {
            if (Constants.isAccessibilityApplied) {
                
                number3Label.accessibilityTraits = .button
            }
            
            number3Check.image = UIImage(named: "uncheck")
            number3Check.tag = UNSELECTED
            
            
        } else if (number3Check.tag == UNSELECTED ){
            if (Constants.isAccessibilityApplied) {
                
                number3Label.accessibilityTraits = .selected
            }
            
            number3Check.image = UIImage(named: "check")
            number3Check.tag = SELECTED
            
            
        }
        
    }
    
    
    
    
    /*
     알파벳 radio 버튼
     */
    
    private func initAlphabetRadio() {
        
        alphabet1Label.accessibilityTraits = .button
        alphabet1Label.isUserInteractionEnabled = true
        alphabet1Label.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onAlphabet1RadioClicked)))
        alphabet1Radio.isUserInteractionEnabled = true
        alphabet1Radio.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onAlphabet1RadioClicked)))
        
        alphabet2Label.accessibilityTraits = .button
        alphabet2Label.isUserInteractionEnabled = true
        alphabet2Label.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onAlphabet2RadioClicked)))
        alphabet2Radio.isUserInteractionEnabled = true
        alphabet2Radio.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onAlphabet2RadioClicked)))
        
        alphabet3Label.accessibilityTraits = .button
        alphabet3Label.isUserInteractionEnabled = true
        alphabet3Label.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onAlphabet3RadioClicked)))
        alphabet3Radio.isUserInteractionEnabled = true
        alphabet3Radio.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(onAlphabet3RadioClicked)))
        
    }
    
    @objc func onAlphabet1RadioClicked(){
        
        if (alphabet1Radio.tag == UNSELECTED) {
            
            alphabet1Radio.image = UIImage(named: "radio_check")
            alphabet1Radio.tag = SELECTED
            
            alphabet2Radio.image = UIImage(named: "radio_uncheck")
            alphabet2Radio.tag = UNSELECTED
            
            alphabet3Radio.image = UIImage(named: "radio_uncheck")
            alphabet3Radio.tag = UNSELECTED
            
            if (Constants.isAccessibilityApplied) {
                alphabet1Label.accessibilityTraits = .selected
                alphabet2Label.accessibilityTraits = .notEnabled
                alphabet3Label.accessibilityTraits = .notEnabled
            }
        }
        
    }
    
    @objc func onAlphabet2RadioClicked(){
        
        if (alphabet2Radio.tag == UNSELECTED) {
            
            alphabet2Radio.image = UIImage(named: "radio_check")
            alphabet2Radio.tag = SELECTED
            
            alphabet1Radio.image = UIImage(named: "radio_uncheck")
            alphabet1Radio.tag = UNSELECTED
            
            alphabet3Radio.image = UIImage(named: "radio_uncheck")
            alphabet3Radio.tag = UNSELECTED
            
            if (Constants.isAccessibilityApplied) {
                alphabet2Label.accessibilityTraits = .selected
                
                alphabet1Label.accessibilityTraits = .notEnabled
                alphabet3Label.accessibilityTraits = .notEnabled
            }
            
        }
        
    }
    
    
    @objc func onAlphabet3RadioClicked(){
        
        if (alphabet3Radio.tag == UNSELECTED) {
            
            alphabet3Radio.image = UIImage(named: "radio_check")
            alphabet3Radio.tag = SELECTED
            
            
            alphabet1Radio.image = UIImage(named: "radio_uncheck")
            alphabet1Radio.tag = UNSELECTED
            
            alphabet2Radio.image = UIImage(named: "radio_uncheck")
            alphabet2Radio.tag = UNSELECTED
            
            if (Constants.isAccessibilityApplied) {
                alphabet3Label.accessibilityTraits = .selected
                alphabet1Label.accessibilityTraits = .notEnabled
                alphabet2Label.accessibilityTraits = .notEnabled
            }
            
        }
        
    }
    
    
    
    
}
