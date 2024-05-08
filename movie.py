#!/usr/bin/env python
import sys

def main():
    # 명령행 인수에서 영화 장르를 가져옴
    genre1 = sys.argv[1] if len(sys.argv) > 1 else None
    genre2 = sys.argv[2] if len(sys.argv) > 2 else None
    sys.stdout.reconfigure(encoding='utf-8')

    # 두 장르가 모두 입력되었는지 확인하고 "아싸"와 입력받은 장르 출력
    if genre1 and genre2:
        print("아싸! 입력한 장르는 '{}'와 '{}'입니다.".format(genre1, genre2))
    else:
        print("두 개의 영화 장르를 모두 입력하세요!")

if __name__ == "__main__":
    main()
