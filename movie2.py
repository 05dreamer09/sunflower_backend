#!/usr/bin/env python
import sys

def main():
    # 명령행 인수에서 영화 장르를 가져옴
    movie1 = sys.argv[1] if len(sys.argv) > 1 else None
    movie2 = sys.argv[2] if len(sys.argv) > 2 else None
    movie3 = sys.argv[2] if len(sys.argv) > 3 else None
    sys.stdout.reconfigure(encoding='utf-8')

    # 두 장르가 모두 입력되었는지 확인하고 "아싸"와 입력받은 장르 출력
    if movie1 and movie2 and movie3:
        print("범죄도시4, 어벤져스2, 파묘, 듄2, 진격의거인")
    else:
        print("두 개의 영화 장르를 모두 입력하세요!")

if __name__ == "__main__":
    main()
